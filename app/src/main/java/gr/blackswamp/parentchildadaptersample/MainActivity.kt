package gr.blackswamp.parentchildadaptersample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import gr.blackswamp.parentchildadaptersample.model.Parent
import gr.blackswamp.parentchildadaptersample.model.ParentChildAdapter
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val parents = buildData()
        list.adapter = ParentChildAdapter(this, parents)
    }

    private fun buildData(): List<Parent> {
        val reply = mutableListOf<Parent>()
        for (idx in 0 until 100) {
            reply.add(Parent(idx).apply {
                dept = "department $idx"
            })
            for (idx2 in 1..5) {
                reply[idx].addChild(idx2, "item $idx - $idx2")
            }
        }
        return reply
    }


}
