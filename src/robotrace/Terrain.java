package robotrace;

import com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL.GL_BLEND;
import static com.jogamp.opengl.GL.GL_FRONT;
import static com.jogamp.opengl.GL.GL_ONE_MINUS_SRC_ALPHA;
import static com.jogamp.opengl.GL.GL_SRC_ALPHA;
import static com.jogamp.opengl.GL.GL_TRIANGLE_STRIP;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SHININESS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SPECULAR;
import com.jogamp.opengl.glu.GLU;
import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.nio.FloatBuffer.wrap;

/**
 * Represents the terrain, to be implemented according to the Assignments.
 */
class Terrain {

    private final int pointsPerSide = 20;
    private double size = 40;
    
    public Terrain() {
        
    }

    /**
     * Draws the terrain.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        Vector p = new Vector(0, 0, 0);
        Vector pp = new Vector(0, 0, 0);
        Vector n = new Vector(0, 0, 0);
        p.z = -1;
        
        double interval = size/((double)pointsPerSide);
        for(int i = 1; i <= pointsPerSide; i++){
            p.x = -(size/2) + i*interval;
            gl.glBegin(GL_TRIANGLE_STRIP);
            for(int j = 0; j <= pointsPerSide; j++){
                p.y = -(size/2) + j*interval;
                p.z = 0.6 * cos(0.3 * p.x + 0.2 * p.y) + 0.4 * cos(p.x - 0.5 * p.y);
                p.z = this.color(gl, p.z);
                pp.x = -p.y;
                pp.y = p.x;
                pp.z = p.z;
                n = p.cross(pp).normalized();
                gl.glNormal3d(n.x, n.y, n.z);
                gl.glVertex3d(p.x, p.y, p.z);
                
                p.z = 0.6 * cos(0.3 * (p.x - interval) + 0.2 * p.y) + 0.4 * cos((p.x - interval) - 0.5 * p.y);
                p.z = this.color(gl, p.z);
                pp.x = -p.y;
                pp.y = p.x;
                pp.z = p.z;
                n = p.cross(pp).normalized();
                gl.glNormal3d(n.x, n.y, n.z);
                gl.glVertex3d(p.x - interval, p.y, p.z);
            }
            gl.glEnd();
        }
        
        gl.glEnable(GL_BLEND);
        gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        gl.glBegin(GL_TRIANGLE_STRIP);
            float[] c = new float[4];
            c[0] = (float)0.7;
            c[1] = (float)0.7;
            c[2] = (float)0.7;
            c[3] = (float)0.3;
            gl.glColor4f(c[0], c[1], c[2], c[3]);
            gl.glNormal3d(0, 0, 1);
            gl.glVertex2d(-(size/2), -(size/2));
            gl.glVertex2d(-(size/2), (size/2));
            gl.glVertex2d((size/2), -(size/2));
            gl.glVertex2d((size/2), (size/2));
        gl.glEnd();
        gl.glDisable(GL_BLEND);
    }

    private double color(GL2 gl, double z) {
        float[] c = new float[4];
        if(z <= 0.0){
            c[0] = 0;
            c[1] = 0;
            c[2] = 1;
            c[3] = 1;
        }else{
            c[0] = (float)max(1 - z, 0.0);
            c[1] = 1;
            c[2] = 0;
            c[3] = 1;
        }
        gl.glColor3f(c[0], c[1], c[2]);
        return z;
    }
}
