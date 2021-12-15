import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//https://developer.android.com/topic/performance/graphics/load-bitmap

final BitmapFactory.Options options = new BitmapFactory.Options();
options.inSampleSize = 1;

Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.image, options);

int imageHeight = bmp.getHeight();
int imageWidth = bmp.getWidth();

int segments = 3;
int segment_length = imageWidth / segments;

int[] averages = new int[segments];

for (int x = 0; x < imageWidth; x++) {
    for (int y = 0; y < imageHeight; y++) {
        int pixel = bmp.getPixel(x, y);

        int r = pixel & 0xff;
        int g = (pixel >> 8) & 0xff;
        int b = (pixel >> 16) & 0xff;

        int index = x / segment_length;

        if (g >= r && g >= b && index < segments) averages[index] += 1;
    }
}

System.out.println(averages[0]);
System.out.println(averages[1]);
System.out.println(averages[2]);
