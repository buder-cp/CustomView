if "%time:~0,1%"==" " set "time=0%time:~1%"

for /f "tokens=*" %%a in ('time/t') do set now=%%a
set ymd=%date:~0,4%%date:~5,2%%date:~8,2%
set hms=%now:~0,2%%now:~3%%time:~6,2%
set dt=%ymd%%hms%
echo %dt%
adb shell logcat -c
adb shell logcat -v threadtime > %dt%.txt