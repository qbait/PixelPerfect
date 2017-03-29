package eu.szwiec.pixelperfect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by szwiec on 29/03/2017.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable divider;

    /**
     * Default divider will be used
     */
    public DividerItemDecoration(Context context) {
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        divider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
    }

    /**
     * Custom divider will be used
     */
    public DividerItemDecoration(Context context, int resId) {
        divider = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View row = parent.getChildAt(i);
            Boolean noDivider = (Boolean) row.getTag(R.id.noSpaceAfter);

            if (noDivider == null || !noDivider) {
                drawDivider(canvas, row, left, right);
            }
        }
    }

    private void drawDivider(Canvas canvas, View row, int left, int right) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) row.getLayoutParams();

        int top = row.getBottom() + params.bottomMargin;
        int bottom = top + divider.getIntrinsicHeight();

        divider.setBounds(left, top, right, bottom);
        divider.draw(canvas);
    }

}
