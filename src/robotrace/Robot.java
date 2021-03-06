package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.*;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static java.nio.FloatBuffer.*;
import java.util.Random;
import static robotrace.Material.*;

/**
* Represents a Robot, to be implemented according to the Assignments.
*/
class Robot {
    
    /** The position of the robot. */
    public Vector position = new Vector(0, 0, 0);
    public Vector firstPos = new Vector(0, 0, 0);
    public boolean firstDrawing = true;
    
    /** The direction in which the robot is running. */
    public Vector direction = new Vector(1, 0, 0);

    /** The material from which this robot is built. */
    private final Material material;
    
    private double bodyWidth    = 0.5;
    private double bodyHeight   = 1.0;
    private double bodyDepth    = 0.4;
    private double legSize      = 0.2;
    private double armSize      = 0.2;
    double headSize     = 0.7;
    
    private double maxAngle     = 30;
    
    private Vector bodyPos = new Vector(0, 0, 1);
    Vector headPos = new Vector(0, 0, (bodyHeight / 2) + (headSize / 2) + 1);
    private Vector leftArmPos = new Vector(-(bodyWidth / 2) - (armSize / 2), 0, (bodyHeight / 2) - (armSize / 2) + 1);
    private Vector rightArmPos = new Vector((bodyWidth / 2) + (armSize / 2), 0, (bodyHeight / 2) - (armSize / 2) + 1);
    private Vector leftLegPos = new Vector(-(bodyWidth / 2) + (legSize / 2), 0, -(bodyHeight / 2) - (legSize / 2) + 1);
    private Vector rightLegPos = new Vector((bodyWidth / 2) - (legSize / 2), 0, -(bodyHeight / 2) - (legSize / 2) + 1);
    final float pace;
    
    
    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material
            
    ) {
        this.material = material;
        this.pace = (float) max(new Random().nextFloat(), 0.5);
        
    }

    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, float tAnim, boolean drawObstacles) {
        if(firstDrawing){
            firstPos.x = position.x;
            firstPos.y = position.y;
            firstPos.z = position.z;
            firstDrawing = false;
        }
        if(drawObstacles){
            gl.glPushMatrix();
            gl.glTranslated(firstPos.x, firstPos.y, firstPos.z);
            gl.glColor3d(0, 0, 0);
            glut.glutSolidCube(1);
            gl.glPopMatrix();
        }
        if(sqrt(pow(position.x - firstPos.x, 2) + pow(position.y - firstPos.y, 2)) < 1){
           position.z += 1 - sqrt(pow(position.x - firstPos.x, 2) + pow(position.y - firstPos.y, 2)); 
        }
        double angle = maxAngle * cos(tAnim * pace * 10);
        double robotAngle = toDegrees(acos(Vector.Y.normalized().dot(this.direction.normalized()))) % 180;
        if(this.direction.x < 0) robotAngle = 180 - robotAngle;
        gl.glPushMatrix();
            gl.glTranslated(this.position.x, this.position.y, this.position.z);
            gl.glRotated(-robotAngle, 0, 0, 1);
            gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, wrap(this.material.diffuse));
            gl.glMaterialfv(GL_FRONT, GL_SPECULAR, wrap(this.material.specular));
            gl.glMaterialf(GL_FRONT, GL_SHININESS, this.material.shininess);

            gl.glPushMatrix();
                gl.glTranslated(bodyPos.x, bodyPos.y, bodyPos.z);
                this.drawBody(gl, glu, glut);
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslated(headPos.x, headPos.y, headPos.z);
                this.drawHead(gl, glu, glut, angle);
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslated(leftArmPos.x, leftArmPos.y, leftArmPos.z);
                this.drawArm(gl, glu, glut, angle);
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslated(rightArmPos.x, rightArmPos.y, rightArmPos.z);
                this.drawArm(gl, glu, glut, -angle);
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslated(leftLegPos.x, leftLegPos.y, leftLegPos.z);
                this.drawLeg(gl, glu, glut, -angle);
            gl.glPopMatrix();

            gl.glPushMatrix();
                gl.glTranslated(rightLegPos.x, rightLegPos.y, rightLegPos.z);
                this.drawLeg(gl, glu, glut, angle);
            gl.glPopMatrix();
        gl.glPopMatrix();
    }
    
    private void drawBody(GL2 gl, GLU glu, GLUT glut){
        gl.glScaled(bodyWidth, bodyDepth, bodyHeight);
        glut.glutSolidCube(1);
    }
    
    private void drawHead(GL2 gl, GLU glu, GLUT glut, double angle){
        gl.glRotated(angle, 1.0, 0, 0);
        gl.glScaled(headSize, headSize, headSize);
        glut.glutSolidCube(1);
    }
    
    private void drawArm(GL2 gl, GLU glu, GLUT glut, double angle){
        gl.glRotated(angle, 1.0, 0, 0);
        gl.glScaled(armSize, armSize, armSize);
        glut.glutSolidCube(1);
        gl.glScaled(1.0 / armSize, 1.0 / armSize, 1.0 / armSize);
        gl.glTranslated(0, 0, -armSize);
        gl.glRotated(angle, 1.0, 0, 0);
        gl.glScaled(armSize, armSize, armSize);
        glut.glutSolidCube(1);
    }
    
    private void drawLeg(GL2 gl, GLU glu, GLUT glut, double angle){
        gl.glRotated(angle, 1.0, 0, 0);
        gl.glScaled(legSize, legSize, legSize);
        glut.glutSolidCube(1);
        gl.glScaled(1.0 / legSize, 1.0 / legSize, 1.0 / legSize);
        gl.glTranslated(0, 0, -legSize);
        gl.glRotated(angle, 1.0, 0, 0);
        gl.glScaled(legSize, legSize, legSize);
        glut.glutSolidCube(1);
    }
}
