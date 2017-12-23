package matteocrippa.it.parrot

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.github.kittinunf.fuse.core.Config
import com.github.kittinunf.fuse.core.Fuse
import com.github.kittinunf.fuse.core.fetch.get
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.imageResource
import java.net.URL

/**
 * Created by Matteo Crippa on 23/12/2017.
 */

fun ImageView.loadImage(url: String?, placeholder: Bitmap? = null, placeholderResource: Int? = null, caching: Parrot.Caching = Parrot.Caching.NetOnly, manipulate: ((data: Bitmap?) -> Bitmap?)? = null, onComplete: ((completed: Boolean) -> Unit)? = null) {

    // if provided set bitmap placeholder
    if (placeholder != null) this.imageBitmap = placeholder

    // if provided set resource placeholder
    if (placeholderResource != null) this.imageResource = placeholderResource

    // if url is empty stop here
    if (url == null) {
        return
    }

    // setup caching
    val config = when (caching) {
        Parrot.Caching.DiskOnly -> {
            Config.DEFAULT_NAME
        }
        Parrot.Caching.NetOnly -> {
            Config.DEFAULT_NAME
        }
        Parrot.Caching.DiskThenNet -> {
            Config.DEFAULT_NAME
        }
    }

    // make remote call using fuel
    Fuse.bytesCache.get(URL(url), configName = config) { data ->

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
                onComplete(true)
            }

        }, failure = { _ ->
            // do nothing and keep placeholder
            // return has not completed
            if (onComplete != null) {
                onComplete(false)
            }
        })

    }
}