# imageViewer-in-Java-Swing
Project Final Report
 

1. Introduction
The purpose of this project is to develop an image viewer application that allows users to view images on their computer. The application will provide a user interface for displaying images and will implement various image operations such as zooming, rotating, and a slideshow functionality. The project aims to provide a user-friendly and visually appealing image viewing experience.

2. Project Implementation
The project is implemented using Java programming language and the Swing framework for creating the graphical user interface. The project consists of multiple class files that work together to create the image viewer application.

2.1. ImageViewerspp Class
This class serves as the entry point of the application. It contains the main method and initializes the ImageViewer class.

2.2. ImageViewer Class
The ImageViewer class is responsible for creating the main application window and managing the user interface components. It extends the JFrame class to create a window for the image viewer. The class includes the following functionalities:

2.2.1. Constructor
- Initializes the JFrame and sets up the basic properties such as the title, size, and layout.
- Creates an instance of the ImagePanel class, which is responsible for displaying the images.
- Creates buttons for various image operations and adds action listeners to handle user interactions.

2.2.2. createIconButton Method
- Creates a JButton with an icon for the image operations buttons.
- Loads the icon image from a file and sets it as the button's icon.
- Configures the button's appearance and behavior, such as size, border, and mouse events.

2.2.3. loadImagesFromFolder Method
- Takes a folder as input and retrieves the image files from the folder.
- Filters the files based on supported image file extensions.
- Sets the currentIndex variable to the first image file.
- Calls the showImage method to display the selected image.

2.2.4. showImage Method
- Loads the selected image file using the Toolkit class.
- Uses a MediaTracker to ensure the image is fully loaded before displaying it.
- Sets the loaded image as the image property of the ImagePanel.
- Updates the window title to include the name of the image file.

2.2.5. Action Listeners
- Includes several inner classes that implement ActionListener interface to handle button click events.
- The ActionListener classes perform the following image operations: rotation, zoom in, zoom out, and slideshow.
- Each action listener updates the imagePanel and repaints it to reflect the changes.

2.3. ImagePanel Class
The ImagePanel class extends JPanel and provides the custom panel for displaying the images. It includes the following functionalities:

2.3.1. Properties
- image: Stores the currently loaded image.
- rotationAngle: Tracks the rotation angle of the image.
- zoomFactor: Tracks the zoom factor of the image.

2.3.2. Constructor
- Sets the preferred size of the panel.

2.3.3. setImage Method
- Sets the image property with the given image.
- Triggers a repaint of the panel to display the new image.

2.3.4. paintComponent Method
- Overrides the paintComponent method to customize the painting behavior.
- Translates and rotates the graphics context based on the rotationAngle property.
- Draws the image with the applied translation, rotation, and zoom.
- Utilizes the zoomFactor property to adjust the size of the displayed image.

3. Conclusion
The image viewer project provides a functional and user-friendly application for viewing images on a computer. It allows users to open a folder, browse through the images, and perform operations like rotating, zooming, and slideshow. The implementation leverages the Java programming language and the Swing framework to create a responsive and visually appealing user interface.
