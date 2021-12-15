import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//https://developer.android.com/topic/performance/graphics/load-bitmap


final BitmapFactory.Options options = new BitmapFactory.Options();

// Reduce the resolution of the bitmap image
options.inSampleSize = 4;

// Get the image as a bitmap
Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.image, options);

// Get the image dimentions
int imageHeight = bmp.getHeight();
int imageWidth = bmp.getWidth();

// Divide the image into segments
int segments = 3;
int segment_length = imageWidth / segments;

// Array containing the total number of green pixels in a segment
int[] averages = new int[segments];

for (int x = 0; x < imageWidth; x++) {
    for (int y = 0; y < imageHeight; y++) {
        
        // Get pixel at x, y coordinate
        int pixel = bmp.getPixel(x, y);

        // Get RGB values from hex
        int r = pixel & 0xff;
        int g = (pixel >> 8) & 0xff;
        int b = (pixel >> 16) & 0xff;

        // Get index in array
        int index = x / segment_length;

        // If pixel is green, add to array
        if (g >= r && g >= b && index < segments) averages[index] += 1;
    }
}

System.out.println(averages[0]);
System.out.println(averages[1]);
System.out.println(averages[2]);
