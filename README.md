# Quickli

A small Java (Kotlin) program to create a custom tray program.

Use at your own risk. Other people with access to your computer could change the commands to execute harmful actions.
This tool is still in development. It's easy to break if you want it to. Please only write valid JSON.

## Install

Download latest `Quickli.jar` file from [releases](https://github.com/JanMalch/Quickli/releases), put it in your autostart (Win+R: `shell:startup`) and run it.
A new tray icon will appear, that helps you getting started.

## Usage

All tray programs are configured via json files in `AppData\Roaming\Quickli`.
To create a tray icon you have to add a json file there. The schema looks like this:

```
{
   "label": "My first tray program",
   "showDefaults": true, // adds "About" and "Edit Entries" buttons, defaults to true
   "image": "my-icon.png", // has to be in `AppData\Roaming\Quickli\images`
   "content": [<Menu>], // the contents of your tray icon
   "leftClick": <Command>, // optional, if not specified the content menu will open
}
```
##### Menu
```
{
   "title": "My first menu entry",
   "children": [<Menu>], // optional
   "command": "explorer.exe /n,C:\\Temp\\my_favorite_file.txt", // optional
   "directory": "", // optional
   "separator": false // optional, entry acts as a separator in the tray menu
}
```

##### Command

```json
{
   "command": "path/to/my.exe",
   "directory": "path/to",
   "envp": ["path optional env parameters"]
}
```

## Examples 

Example with nested menus and separators

```json
{
   "label":"Quickli",
   "content":[
      {
         "title":"Android Studio",
         "command":"C:/Program Files/Android/Android Studio/bin/studio64.exe",
         "directory":"C:/Program Files/Android/Android Studio/bin"
      },
      {
         "title":"Shutdown in 1 hour",
         "command":"shutdown -s -f -t 3600"
      },
      {
         "separator":true
      },
      {
         "title":"VMs",
         "children":[
            {
               "title":"dataiku",
               "command":"C:/Program Files/Oracle/VirtualBox/VBoxManage.exe startvm dataiku-dss-5.0.2",
               "directory":"C:/Program Files/Oracle/VirtualBox"
            },
            {
               "title": "This is disabled"
            }
         ]
      }
   ]
}
```

---

Open a program with left click, but show the menu on right click.

```json
{
  "label": "Android Studio",
  "image": "Android_Studio_icon.png",
  "showDefaults": false,
  "leftClick": {
    "command": "C:/Program Files/Android/Android Studio/bin/studio64.exe",
    "directory": "C:/Program Files/Android/Android Studio/bin"
  },
  "content": [
    {
      "title": "Android Studio",
      "command": "C:/Program Files/Android/Android Studio/bin/studio64.exe",
      "directory": "C:/Program Files/Android/Android Studio/bin"
    }
  ]
}
```

---

You can create trays for programs that you wish had one:

```json
{
  "label": "VirtualBox VMs",
  "showDefaults": false,
  "image": "VirtualBox-icon.png",
  "content": [
    {
      "title": "VirtualBox",
      "command": "C:/Program Files/Oracle/VirtualBox/VirtualBox.exe",
      "directory": "C:/Program Files/Oracle/VirtualBox"
    },
    {
      "separator": true
    },
    {
      "title": "Kali Linux",
      "command": "C:/Program Files/Oracle/VirtualBox/VBoxManage.exe startvm Kali-Linux-2018.4-vbox-amd64",
      "directory": "C:/Program Files/Oracle/VirtualBox"
    },
    {
      "title": "dataiku",
      "command": "C:/Program Files/Oracle/VirtualBox/VBoxManage.exe startvm dataiku-dss-5.0.2",
      "directory": "C:/Program Files/Oracle/VirtualBox"
    }
  ]
}
```

![quickli virtual box tray icon](https://user-images.githubusercontent.com/25508038/51085582-f3110900-173b-11e9-9b5f-16dde24e5ad5.png)
![quickli virtual box menu](https://user-images.githubusercontent.com/25508038/51085586-f906ea00-173b-11e9-8831-e0e488717cbb.png)
