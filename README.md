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
compile 'com.github.matteocrippa:Parrot:0.0.1'
```

# Usage
`Parrot` provides you a simple function to tame all your needs:

`ImageView.loadImage`

This function has the following parameters:
- url, _optional_ string with current remote image url
- placeholder, _optional_ bitmap placeholder
- placeholderResource, _optional_ resource placeholder
- caching, you can set your favorite caching system, see below
- manipulate, _optional_ callback returns the image before setting to your imageview, here you can tweak the image
- onComplete, _optional_ callback returns when the image has been applied to the imageview


## Caching
`Parrot` provide an easy way to handle images caching:

- DiskOnly, download once, and then always read from disk
- NetOnly, _default_ option, force always reloading from net
- DiskThenNet, download once, and then refresh from net

#### Credits

Icon is taken by free emojii set by [Vincent Le Moign](https://dribbble.com/webalys)