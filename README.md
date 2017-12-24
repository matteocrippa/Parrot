# Parrot
A kotlin extension to load easily remote images in your `ImageView`.

![parrot](https://github.com/matteocrippa/parrot/blob/master/.github/parrot.png?raw=true)

# Install

Add to **gradle** in _allprojects_

```
maven { url 'https://jitpack.io' }
```

then add this

```
compile 'com.github.matteocrippa:Parrot:0.0.3'
```

# Usage
`Parrot` needs to be init once, passing the app context:

```kotlin
Parrot(context)
```

then provides you a simple function to tame all your needs:

`ImageView.load`

This function has the following parameters:
- `url`, _optional_ string with current remote image url
- `placeholder`, _optional_ bitmap placeholder or resource Id
- `caching`, you can set your favorite caching system, see below
- `manipulate`, _optional_ callback returns the image before setting to your imageview, here you can tweak the image
- `onComplete`, _optional_ callback returns when the image has been applied to the imageview


## Caching
`Parrot` provide an easy way to handle images caching:

- `NetOnly`, _default_ option, force always reloading from net
- `NetThenDisk`, download once and use local version

## Examples

Simple usage
```kotlin

imageView.loadImage("http://the-most-awesome-image.png")
```

Callback usage
```kotlin
imageView.loadImage("http://the-most-awesome-image.png", 
    manipulate = { bitmap ->
        // alter bitmap
        bitmap
    }, onComplete = { completed ->
        // do something
    }                                        

```

#### Credits

Icon is taken by free emojii set by [Vincent Le Moign](https://dribbble.com/webalys)
