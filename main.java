import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//https://developer.android.com/topic/performance/graphics/load-bitmap

final BitmapFactory.Options options = new BitmapFactory.Options();
options.inJustDecodeBounds = true;

BitmapFactory.decodeResource(getResources(), R.drawable.test, options);

int imageHeight = options.outHeight;
int imageWidth = options.outWidth;

int segments = 3;
int segment_length = imageWidth / segments;

int[] averages = new int[segments];

options.inSampleSize = 2;
options.inJustDecodeBounds = false;

Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.test, options);

System.out.println("seg length: " + segment_length);
System.out.println("array length: " + averages.length);
System.out.println("image width: " + imageWidth);

for (int x = 0; x < imageWidth; x++)
{
    for (int y = 0; y < imageHeight; y++)
    {
        int pixel = (bmp.getPixel(x, y) >> 8) & 0xff;
        int index = x / segment_length;

        if (index < segments) averages[index] += pixel;
    }
}

int divisor = segment_length * imageHeight;

for(int i = 0; i < segments; i++)
{
    averages[i] = averages[i] / divisor;
}

System.out.println(averages[2]);
System.out.println(averages[1]);
System.out.println(averages[0]);
