package matteocrippa.it.parrot

import android.content.Context
import com.github.kittinunf.fuse.core.Fuse

/**
 * Created by matteocrippa on 23/12/2017.
 */
class Parrot(context: Context) {
    enum class Caching {
        NetOnly, NetThenDisk
    }

    init {
        Fuse.init(context.cacheDir.absolutePath)
    }
}