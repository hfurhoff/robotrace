package robotrace;

/**
* Materials that can be used for the robots.
*/
public enum Material {

    /** 
     * Gold material properties.
     * Modify the default values to make it look like gold.
     */
    GOLD (
            
        new float[] {(float) 0.34, (float) 0.31, (float) 0.09, (float) 1.0},
        new float[] {(float) 0.79, (float) 0.72, (float) 0.20, (float) 1.0},
        83

    ),

    /**
     * Silver material properties.
     * Modify the default values to make it look like silver.
     */
    SILVER (
            
        new float[] {(float) 0.27, (float) 0.27, (float) 0.27, (float) 1.0},
        new float[] {(float) 0.77, (float) 0.77, (float) 0.77, (float) 1.0},
        59

    ),

    /** 
     * Orange material properties.
     * Modify the default values to make it look like orange.
     */
    ORANGE (
            
        new float[] {(float) 1.0, (float) 0.5, 0, 1},
        new float[] {(float) 0.4, (float) 0.2, (float) 0.0, 1},
        128

    ),

    /**
     * Wood material properties.
     * Modify the default values to make it look like Wood.
     */
    WOOD (

        new float[] {(float) 0.64, (float) 0.32, 0, 1},
        new float[] {(float) 0.3, (float) 0.3, (float) 0.3, 1},
        2

    );

    /** The diffuse RGBA reflectance of the material. */
    float[] diffuse;

    /** The specular RGBA reflectance of the material. */
    float[] specular;
    
    /** The specular exponent of the material. */
    float shininess;

    /**
     * Constructs a new material with diffuse and specular properties.
     */
    private Material(float[] diffuse, float[] specular, float shininess) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }
}
