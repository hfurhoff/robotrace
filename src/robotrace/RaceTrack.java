package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import static com.jogamp.opengl.GL2.*;

/**
 * Implementation of a race track that is made from Bezier segments.
 */
abstract class RaceTrack {
    
    /** The width of one lane. The total width of the track is 4 * laneWidth. */
    private final static float laneWidth = 1.22f;
    
    
    
    /**
     * Constructor for the default track.
     */
    public RaceTrack() {
    }


    
    /**
     * Draws this track, based on the control points.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        double polyPoints = 100;    // Number of polygons on sides (boundaries) of race track 
        double toggleSign = 1;      // Toggles points between positive and negative z-plane
        
        // Drawing the OUTER FRAME of the race track
        gl.glBegin(GL_TRIANGLE_STRIP);
        for(int i=0;i<=polyPoints+1;i++){
            gl.glColor3d(0.8, 1, 0.9);      // Define color
            
            // Calculate normal vector
            Vector normal = new Vector(-getTangent(i / polyPoints).y, getTangent(i / polyPoints).x, 0);
            
            // Pass next triangle strip point on to glVertex, depending on defined number of polygons (polyPoints)
            gl.glVertex3d(getPoint(i / polyPoints).x + (normal.normalized().x * 2 * laneWidth),
                            getPoint(i / polyPoints).y + (normal.normalized().y * 2 * laneWidth), toggleSign); 
            
            toggleSign = -toggleSign;   // Toggle z-plane
        }
        gl.glEnd();
        
        // Drawing the INNER FRAME of the race track
        gl.glBegin(GL_TRIANGLE_STRIP);
        for(int i=0;i<=polyPoints+1;i++){
            gl.glColor3d(0.8, 1, 0.9);      // Define color
            
            // Calculate normal vector
            Vector normal = new Vector(-getTangent(i / polyPoints).y, getTangent(i / polyPoints).x, 0);

            // Pass next triangle strip point on to glVertex, depending on defined number of polygons (polyPoints)
            gl.glVertex3d(getPoint(i / polyPoints).x - (normal.normalized().x * 2 * laneWidth),
                            getPoint(i / polyPoints).y - (normal.normalized().y * 2 * laneWidth),toggleSign);

            toggleSign = -toggleSign;   // Toggle z-plane
        }
        gl.glEnd();
        
        // Drawing the SURFACE of the race track
        gl.glBegin(GL_TRIANGLE_STRIP);
        for(int i=0;i<=polyPoints+1;i++){
            gl.glColor3d(0.9, 1, 0.9);      // Define color
            
            // Calculate normal vector
            Vector normal = new Vector(-getTangent(i / polyPoints).y, getTangent(i / polyPoints).x, 0);
            
            // Pass next triangle strip point on to glVertex, depending on defined number of polygons (polyPoints)
            gl.glVertex3d(getPoint(i / polyPoints).x - (normal.normalized().x * 2 * laneWidth * toggleSign), 
                            getPoint(i / polyPoints).y - (normal.normalized().y * 2 * laneWidth * toggleSign),1);
           
            toggleSign = -toggleSign;   // Toggle z-plane
        }
        gl.glEnd();
        
    }
    
    /**
     * Returns the center of a lane at 0 <= t < 1.
     * Use this method to find the position of a robot on the track.
     */
    public Vector getLanePoint(int lane, double t){
        // Calculate normal vector
        Vector normal = new Vector(-getTangent(t).y, getTangent(t).x, 0);
        
        // Return position according to lane number (0,1,2,3)
        return new Vector(getPoint(t).x - (normal.normalized().x * 1.5 * laneWidth) + (normal.normalized().x * lane * laneWidth), 
                getPoint(t).y - (normal.normalized().y * 1.5 * laneWidth) + (normal.normalized().y * lane * laneWidth),1);
    }
    
    /**
     * Returns the tangent of a lane at 0 <= t < 1.
     * Use this method to find the orientation of a robot on the track.
     */
    public Vector getLaneTangent(int lane, double t){
        // Calculate normal vector
        Vector normal = new Vector(-getTangent(t).y, getTangent(t).x, 0);
        
        // Return tangent according to lane number (0,1,2,3)
        return new Vector(getTangent(t).x - (normal.normalized().x * 1.5 * laneWidth) + (normal.normalized().x * lane * laneWidth), 
                getTangent(t).y - (normal.normalized().y * 1.5 * laneWidth) + (normal.normalized().y * lane * laneWidth),1);
    }
    
    
    
    // Returns a point on the test track at 0 <= t < 1.
    protected abstract Vector getPoint(double t);

    // Returns a tangent on the test track at 0 <= t < 1.
    protected abstract Vector getTangent(double t);
}
