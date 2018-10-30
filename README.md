# Quickli

A small Kotlin program to create a custom tray program.

Use at your own risk. Other people with access to your computer could change the commands to execute harmful actions.
This tool is still in development. It's easy to break if you want it to. Please only write valid JSON.

## Example File

```json
[
  {
    "title": "Android Studio",
    "command": "C:/Program Files/Android/Android Studio/bin/studio64.exe",
    "directory": "C:/Program Files/Android/Android Studio/bin"
  },
  {
    "title": "Shutdown in 1 hour",
    "command": "shutdown -s -f -t 3600"
  },
  {
    "separator": true
  },
  {
    "title": "VMs",
    "children": [
      {
        "title": "dataiku",
        "command": "C:/Program Files/Oracle/VirtualBox/VBoxManage.exe startvm dataiku-dss-5.0.2",
        "directory": "C:/Program Files/Oracle/VirtualBox"
      }
    ]
  }
]
```
