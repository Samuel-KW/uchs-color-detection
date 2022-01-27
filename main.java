import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//https://developer.android.com/topic/performance/graphics/load-bitmap

final BitmapFactory.Options options = new BitmapFactory.Options();
options.inSampleSize = 1;

double scale = 0.01;

Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image, options);
Bitmap bmp = Bitmap.createScaledBitmap(originalBitmap, (int)(originalBitmap.getWidth() * scale), (int)(originalBitmap.getHeight() * scale), false);

long startTime = System.nanoTime();
System.out.println("Starting timer.");

int segments = 3;

// Get the image dimensions
int imageHeight = bmp.getHeight();
int imageWidth = bmp.getWidth();

System.out.print("Array size: ");
System.out.println(imageHeight * imageWidth);

//int[] pixels = new int[imageWidth * imageHeight];
//bmp.getPixels(pixels, 0, bm.getWidth(), 0, 0, width, height);

// Divide the image into segments
int segment_length = imageWidth / segments;

// Array containing the total number of green pixels in a segment
int[] averages = new int[segments];

for (int x = 0; x < imageWidth; x++) {
    for (int y = 0; y < imageHeight; y++) {

        // Get pixel at x, y coordinate
        int pixel = bmp.getPixel(x, y);

        // Get RGB values from hex
        int r = Color.red(pixel);
        int g = Color.green(pixel);
        int b = Color.blue(pixel);

        // Get index in array
        int index = x / segment_length;

        // If pixel is green, add to array
        if (g >= r && g >= b && index < segments) {
            averages[index] += 1;
            //bmp.setPixel(x, y, 0xFFFFFFFF);
        } else {
            //bmp.setPixel(x, y, 0xFF000000);
        }

    }
}

long estimatedTime = (System.nanoTime() - startTime) / 1000000000;
System.out.print("Time elapsed (s): ");
System.out.println(estimatedTime);

System.out.println(averages[0]);
System.out.println(averages[1]);
System.out.println(averages[2]);

int section = 0;
int section_size = 0;

// Get section with most green
for (int i = 0; i < averages.length; ++i) {
    if (averages[i] > section_size) {
        section = i;
        section_size = averages[i];
    }
}

System.out.print("Target Height: ");
System.out.println(section);
