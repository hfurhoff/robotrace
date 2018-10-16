package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.*;

/**
* Represents a Robot, to be implemented according to the Assignments.
*/
class Robot {
    
    /** The position of the robot. */
    public Vector position = new Vector(0, 0, 0);
    
    /** The direction in which the robot is running. */
    public Vector direction = new Vector(1, 0, 0);

    /** The material from which this robot is built. */
    private final Material material;
    
    private Vector bodyPos = new Vector(0, 0, 0);
    private Vector headPos = new Vector(0, 0, 0.7);
    private Vector leftArmPos = new Vector(-0.4, 0, 0.35);
    private Vector rightArmPos = new Vector(0.4, 0, 0.35);
    private Vector leftLegPos = new Vector(-0.25, 0, -0.7);
    private Vector rightLegPos = new Vector(0.25, 0, -0.7);
    
    
    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material
            
    ) {
        this.material = material;

        
    }

    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        gl.glPushMatrix();
            this.drawBody();
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(headPos.x, headPos.y, headPos.z);
            this.drawHead();
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(leftArmPos.x, leftArmPos.y, leftArmPos.z);
            this.drawArm();
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(rightArmPos.x, rightArmPos.y, rightArmPos.z);
            this.drawArm();
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(leftLegPos.x, leftLegPos.y, leftLegPos.z);
            this.drawLeg();
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(rightLegPos.x, rightLegPos.y, rightLegPos.z);
            this.drawLeg();
        gl.glPopMatrix();
    }
    
    private void drawBody(){
        
    }
    
    private void drawHead(){
        
    }
    
    private void drawArm(){
        
    }
    
    private void drawLeg(){
        
    }
}
