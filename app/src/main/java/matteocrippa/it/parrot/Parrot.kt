package matteocrippa.it.parrot

import android.content.Context
import com.github.kittinunf.fuse.core.Fuse

/**
 * Created by matteocrippa on 23/12/2017.
 */
class Parrot(private val context: Context) {
    enum class Caching {
        DiskOnly, NetOnly, DiskThenNet
    }

    init {
        Fuse.init(context.cacheDir.absolutePath)
    }
}