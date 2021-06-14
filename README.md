# VFE-with-API

A modified version of the [Vivecraft Forge Extensions mod](https://github.com/Techjar/VivecraftForgeExtensions_110) to allow mod developers to interact with VR-specific things. 

## Current Support

There is currently only support for Vivecraft 1.16. Older versions of Minecraft are NOT planned.

## Installation for Everyone

1. Download and place this mod into your `mods` folder. This has to be done both for individual clients, and the server!
2. Delete any instance of the regular `VivecraftForgeExtensions` mod, as this mod is built off of, and includes all the functionality of it!

## Installation for Developers

1. Add the following lines to your `build.gradle`:
```
repositories {
    maven {
            name = "VivecraftAPI"
            url = "http://blf02.net:4567"
        }
}
```
2. Under the dependencies section of your `build.gradle`, add the following line: `compile fg.deobf("net.blf02:vfewithapi:VERSION_NUMBER")`, where `VERSION_NUMBER` is the version number of the Vivecraft-API you would like to use (such as `1.0.0`).
3. Close and re-open your IDE.
4. If you're still unable to access Vivecraft-API things, run `gradlew --refresh-dependencies`, than restart your IDE again.

## Credits

A lot of work for this mod has been done by Techjar, as this is based heavily off of Techjar's [VivecraftForgeExtensions](https://github.com/Techjar/VivecraftForgeExtensions_110).

## Mod Pack Permissions
The modpack permissions for this mod mirror those of the VivecraftForgeExtensions repository (the repository this one was forked from). Please [head there](https://github.com/Techjar/VivecraftForgeExtensions_110) for information related to that.