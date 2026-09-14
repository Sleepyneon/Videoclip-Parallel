@echo off
ffmpeg -y -framerate 12 -i ..\output\frames_parallel\frame_%%03d.png -i ..\input\audio\audio_base.wav -vf "scale=trunc(iw/2)*2:trunc(ih/2)*2" -c:v libx264 -pix_fmt yuv420p -shortest ..\output\videoclip_parallel.mp4
if %errorlevel% neq 0 (
    scripts\FFmpeginstaller -y -framerate 12 -i ..\output\frames_parallel\frame_%%03d.png -i ..\input\audio\audio_base.wav -vf "scale=trunc(iw/2)*2:trunc(ih/2)*2" -c:v libx264 -pix_fmt yuv420p -shortest ..\output\videoclip_parallel.mp4
)
pause