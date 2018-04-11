package matteocrippa.it.parrot

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.github.kittinunf.fuse.core.Fuse
import com.github.kittinunf.fuse.core.fetch.get
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.imageResource
import java.net.URL

/**
 * Created by Matteo Crippa on 23/12/2017.
 */

fun ImageView.load(url: String?, placeholder: Any? = null, caching: Parrot.Caching = Parrot.Caching.NetThenDisk, manipulate: ((data: Bitmap?) -> Bitmap?)? = null, onPlaceholder: ((completed: Boolean) -> Unit)? = null, onComplete: ((error: Exception?) -> Unit)? = null) {

    if(placeholder == null) {
        this.loadImage(url, caching = caching, manipulate = manipulate, onComplete = onComplete)
    } else {
        if(placeholder is Int) {
            this.loadImage(url, placeholderResource = placeholder, caching = caching, manipulate = manipulate, onPlaceholder = onPlaceholder, onComplete = onComplete)
        } else if (placeholder is Bitmap) {
            this.loadImage(url, placeholder = placeholder, caching = caching, manipulate = manipulate, onPlaceholder = onPlaceholder, onComplete = onComplete)
        }
    }
}

private fun ImageView.loadImage(url: String?, placeholder: Bitmap? = null, placeholderResource: Int? = null, caching: Parrot.Caching = Parrot.Caching.NetThenDisk, manipulate: ((data: Bitmap?) -> Bitmap?)? = null, onPlaceholder: ((completed: Boolean) -> Unit)? = null, onComplete: ((error: Exception?) -> Unit)? = null) {

    // placehoder set flag
    var placeholderSet = false

    // if provided set bitmap placeholder
    if (placeholder != null) {
        this.imageBitmap = placeholder
        placeholderSet = true
    }

    // if provided set resource placeholder
    if (placeholderResource != null) {
        this.imageResource = placeholderResource
        placeholderSet = true
    }

    // placeholder callback
    if(onPlaceholder != null) {
        onPlaceholder(placeholderSet)
    }

    // if url is empty stop here
    if (url == null) {
        return
    }

    // setup caching
    when (caching) {
        Parrot.Caching.NetOnly -> {
            Fuse.bytesCache.remove(url)
        }
        else -> {}
    }

    // make remote call using fuel
    Fuse.bytesCache.get(URL(url)) { data ->

        data.fold(success = { bytes ->
            // convert bytearray to bitmap
            val data = BitmapFactory.decodeByteArray(bytes, 0, bytes.count())

            // if we have our manipulate callback
            if (manipulate != null) {
                // use the manipulated data
                this.imageBitmap = manipulate(data)
            } else {
                // otherwise use stock data
                this.imageBitmap = data
            }

            // return has completed
            if (onComplete != null) {
                onComplete(null)
            }

        }, failure = { error ->
            // do nothing and keep placeholder
            // return has not completed
            if (onComplete != null) {
                onComplete(error)
            }
        })

    }
}