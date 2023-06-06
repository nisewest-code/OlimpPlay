package com.olim.pplay.support

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Point
import android.os.Build
import android.view.DragEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import com.olim.pplay.R
import com.olim.pplay.databinding.ActivityBoardGameOlimpBinding
import kotlin.properties.Delegates

class GameSupport(private val binding: ActivityBoardGameOlimpBinding, callbackPerson1:(x: Float, y: Float)->Unit, callbackPerson2:(x: Float, y: Float)->Unit) {
    @SuppressLint("ClickableViewAccessibility")
    private var item = 0
    fun attachViewDragListener() {

        // 1
        binding.person1.setOnTouchListener { view: View, event ->
            item = 0
            // 2
            val item = ClipData.Item("f")

            // 3
            val dataToDrag = ClipData(
                "f",
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item
            )

            // 4
            val maskShadow = MaskDragShadowBuilder(view)

            // 5
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                //support pre-Nougat versions
                @Suppress("DEPRECATION")
                view.startDrag(dataToDrag, maskShadow, view, 0)
            } else {
                //supports Nougat and beyond
                view.startDragAndDrop(dataToDrag, maskShadow, view, 0)
            }

            // 6
            view.visibility = View.INVISIBLE

            //7
            true
        }

        binding.person2.setOnTouchListener { view: View, event ->
            item = 1
            // 2
            val item = ClipData.Item("f")

            // 3
            val dataToDrag = ClipData(
                "f",
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item
            )

            // 4
            val maskShadow = MaskDragShadowBuilder(view)

            // 5
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                //support pre-Nougat versions
                @Suppress("DEPRECATION")
                view.startDrag(dataToDrag, maskShadow, view, 0)
            } else {
                //supports Nougat and beyond
                view.startDragAndDrop(dataToDrag, maskShadow, view, 0)
            }

            // 6
            view.visibility = View.INVISIBLE

            //7
            true
        }
    }

    private class MaskDragShadowBuilder(view: View) : View.DragShadowBuilder(view) {

        //1
        private val shadow = ResourcesCompat.getDrawable(
            view.context.resources,
            R.drawable.person1,
            view.context.theme
        )

        // 2
        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            // 3
            val width: Int = view.width

            // 4
            val height: Int = view.height

            // 5
            shadow?.setBounds(0, 0, width, height)

            // 6
            size.set(width, height)

            // 7
            touch.set(width / 2, height / 2)
        }

        // 8
        override fun onDrawShadow(canvas: Canvas) {
            // 9
            shadow?.draw(canvas)
        }
    }

    var currentX by Delegates.notNull<Float>()
    var currentY by Delegates.notNull<Float>()

    val maskDragListener = View.OnDragListener { view, dragEvent ->

        //2
        val draggableItem = dragEvent.localState as View

        //3
        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> {

                true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                currentX = dragEvent.x
                currentY = dragEvent.y

                if (item == 0){

                    callbackPerson1(currentX, currentY)
                } else if (item == 1){
                    callbackPerson2(currentX, currentY)
                }

                try {
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                binding.container.alpha = 1.0f
                draggableItem.visibility = View.VISIBLE
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                //1
                binding.container.alpha = 1.0f
                //2
                if (dragEvent.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    val draggedData = dragEvent.clipData.getItemAt(0).text
                    //TODO : perform any action on the draggedData
                }
                draggableItem.x = dragEvent.x - (draggableItem.width / 2)
//2
                draggableItem.y = dragEvent.y - (draggableItem.height / 2)

//3
                val parent = draggableItem.parent as RelativeLayout
//4
                parent.removeView(draggableItem)

//5
                val dropArea = view as RelativeLayout
//6
                dropArea.addView(draggableItem)
                checkIfMaskIsOnFace(dragEvent)
//7
                //3
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                draggableItem.visibility = View.VISIBLE
                view.invalidate()
                true
            }
            else -> {
                false
            }
        }
    }

    private val maskOn = "FOrest Mask on"
    private val maskOff = "Forest Mask off"

    private fun checkIfMaskIsOnFace(dragEvent: DragEvent) {
        //1
        val faceXStart = binding.container.x
        val faceYStart = binding.container.y

        //2
        val faceXEnd = faceXStart + binding.container.width
        val faceYEnd = faceYStart + binding.container.height
        //3
        val toastMsg =
            if (dragEvent.x in faceXStart..faceXEnd && dragEvent.y in faceYStart..faceYEnd) {
                maskOn
            } else {
                maskOff
            }
        //4
    }
}