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
    
    private double bodyWidth    = 0.5;
    private double bodyHeight   = 1.0;
    private double bodyDepth    = 0.4;
    private double legSize      = 0.4;
    private double armSize      = 0.3;
    private double headSize     = 0.4;

    
    private Vector bodyPos = new Vector(0, 0, 0);
    private Vector headPos = new Vector(0, 0, (bodyHeight / 2) + (headSize / 2));
    private Vector leftArmPos = new Vector(-(bodyWidth / 2) - (armSize / 2), 0, (bodyHeight / 2) - (armSize / 2));
    private Vector rightArmPos = new Vector((bodyWidth / 2) + (armSize / 2), 0, (bodyHeight / 2) - (armSize / 2));
    private Vector leftLegPos = new Vector(-(bodyWidth / 2) - (legSize / 2), 0, -(bodyHeight / 2) - (legSize / 2));
    private Vector rightLegPos = new Vector((bodyWidth / 2) + (legSize / 2), 0, -(bodyHeight / 2) - (legSize / 2));
    
    
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
            this.drawBody(gl, glu, glut);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(headPos.x, headPos.y, headPos.z);
            this.drawHead(gl, glu, glut);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(leftArmPos.x, leftArmPos.y, leftArmPos.z);
            this.drawArm(gl, glu, glut);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(rightArmPos.x, rightArmPos.y, rightArmPos.z);
            this.drawArm(gl, glu, glut);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(leftLegPos.x, leftLegPos.y, leftLegPos.z);
            this.drawLeg(gl, glu, glut);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
            gl.glTranslated(rightLegPos.x, rightLegPos.y, rightLegPos.z);
            this.drawLeg(gl, glu, glut);
        gl.glPopMatrix();
    }
    
    private void drawBody(GL2 gl, GLU glu, GLUT glut){
        gl.glScaled(bodyWidth, bodyDepth, bodyHeight);
        glut.glutSolidCube(1);
    }
    
    private void drawHead(GL2 gl, GLU glu, GLUT glut){
        
    }
    
    private void drawArm(GL2 gl, GLU glu, GLUT glut){
        
    }
    
    private void drawLeg(GL2 gl, GLU glu, GLUT glut){
        
    }
}
