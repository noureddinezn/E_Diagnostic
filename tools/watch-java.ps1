$ErrorActionPreference = "Stop"

$projectRoot = Join-Path $PSScriptRoot "..\Project"
$javaRoot = Join-Path $projectRoot "src\main\java"
$lastSnapshot = ""

Write-Host "Java auto-compile watcher started."

while ($true) {
    $snapshot = (Get-ChildItem -Path $javaRoot -Filter *.java -Recurse -File |
        Sort-Object FullName |
        ForEach-Object { "$($_.FullName)|$($_.LastWriteTimeUtc.Ticks)|$($_.Length)" }) -join "`n"

    if ($lastSnapshot -and $snapshot -ne $lastSnapshot) {
        Write-Host "Java change detected. Compiling..."
        Push-Location $projectRoot
        try {
            & mvn -q -DskipTests compile
            if ($LASTEXITCODE -eq 0) {
                Write-Host "Java compile completed. Tomcat reload will pick up the classes."
            } else {
                Write-Warning "Maven compile failed with exit code $LASTEXITCODE."
            }
        } finally {
            Pop-Location
        }
    }

    $lastSnapshot = $snapshot
    Wait-Event -Timeout 1 | Out-Null
}
