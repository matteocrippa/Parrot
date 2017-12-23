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
compile 'com.github.matteocrippa:parrot:0.0.1'
```

# Usage
`Parrot` provides you a simple function to tame all your needs:

`ImageView.loadImage`

This function has the following parameters:
- url: String?
- placeholder: Bitmap? = null
- placeholderResource: Int? = null
- caching: Parrot.Caching = Parrot.Caching.NetOnly
- manipulate: ((data: Bitmap?) -> Bitmap?)? = null, 
- onComplete: ((completed: Boolean) -> Unit)? = null)


#### Credits

Icon is taken by free emojii set by [Vincent Le Moign](https://dribbble.com/webalys)