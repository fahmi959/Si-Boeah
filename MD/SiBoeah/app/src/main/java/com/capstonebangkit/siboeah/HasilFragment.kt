import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.capstonebangkit.siboeah.R

class HasilFragment : Fragment() {
    // Fragment implementation for each image
    // You can customize this based on your image handling logic

    // Add private properties to store the values
    private var namaBuah: String? = null
//    private var tingkatKesegaran: String? = null

    companion object {
        fun newInstance(namaBuah: String, ): HasilFragment {
            val fragment = HasilFragment()
            val args = Bundle()
            args.putString("nama_buah", namaBuah)
//            args.putString("tingkat_kesegaran", tingkatKesegaran)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the values from arguments
        arguments?.let {
            namaBuah = it.getString("nama_buah")
//            tingkatKesegaran = it.getString("tingkat_kesegaran")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate your image view fragment layout here
        val view = inflater.inflate(R.layout.fragment_hasil, container, false)

        // Update the views with the retrieved values
        view.findViewById<TextView>(R.id.namaBuahTextView).text = namaBuah
//        view.findViewById<TextView>(R.id.tingkatKesegaranTextView).text = tingkatKesegaran

        return view
    }
}