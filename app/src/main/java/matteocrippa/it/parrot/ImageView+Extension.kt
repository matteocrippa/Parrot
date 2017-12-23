package matteocrippa.it.parrot

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.github.kittinunf.fuel.Fuel
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.imageResource

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

    // TODO: add caching
    when (caching) {
        Parrot.Caching.DiskOnly -> {
        }
        Parrot.Caching.NetOnly -> {
        }
        Parrot.Caching.DiskThenNet -> {
        }
    }

    // make remote call using fuel
    Fuel.get(url).response { _, _, data ->

        data.fold(success = { bytes ->
            // convert bytearray to bitmap
            val data = BitmapFactory.decodeByteArray(bytes, 0, bytes.count())
            // TODO: store on disk the resource
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