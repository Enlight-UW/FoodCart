package club.enlight.foodtruck.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import club.enlight.foodtruck.data.local.DbBitmapUtility;
import club.enlight.foodtruck.data.local.DbHelper;
import club.enlight.foodtruck.data.model.Doodle;

/**
 * Created by daniel on 11/27/17.
 */

public class CanvasView extends View {

    private static final String TAG = CanvasView.class.getSimpleName();

    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    Context context;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;

        // make a new Path
        mPath = new Path();
        //NOTE: The way this is, we only have one path, so any and all changes to the path would
        //affect the entire path. To change this, you can create a currPath which changes with each
        //mouse press, and you would also need a currPaint

        // and we set a new Paint with the desired attributes (can be changed if you want)
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f*4);
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the new mPath with the new mPaint on the canvas when onDraw
        canvas.drawPath(mPath, mPaint);
    }

    //When the server is updated
    public void updateMe() {
        String fromUp = null;//fetch the byte array from the upstream
        mBitmap = DbBitmapUtility.getImage(fromUp);
        mCanvas = new Canvas(mBitmap);
    }

    public byte[] privatePost(View v, double truckId) {
        byte[] map = new byte[1];
        try {
            map = DbBitmapUtility.getBytes(mBitmap);
        }
        catch(Exception e) {
            Log.d(TAG, "Failed to convert image");
        }
        DbHelper db = new DbHelper(this.context);
        Doodle pic = new Doodle(truckId);
        pic.setRawImage(map);
        db.insertImage(pic);
        return map;
    }

    // when ACTION_DOWN start touch according to the x,y values
    private void startTouch(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas() {
        mPath.reset();
        invalidate();
    }

    // when ACTION_UP stop touch
    private void upTouch() {
        mCanvas.drawPath(mPath, mPaint);
    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                break;
        }
        invalidate();
        return true;
    }
}
