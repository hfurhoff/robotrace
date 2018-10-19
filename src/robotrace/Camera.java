package robotrace;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Implementation of a camera with a position and orientation. 
 */
class Camera {

    /** The position of the camera. */
    public Vector eye = new Vector(3f, 6f, 5f);

    /** The point to which the camera is looking. */
    public Vector center = Vector.O;

    /** The up vector. */
    public Vector up = Vector.Z;

    /**
     * Updates the camera viewpoint and direction based on the
     * selected camera mode.
     */
    public void update(GlobalState gs, Robot focus) {

        switch (gs.camMode) {
            
            // First person mode    
            case 1:
                setFirstPersonMode(gs, focus);
                break;
                
            // Default mode    
            default:
                setDefaultMode(gs);
        }
    }

    /**
     * Computes eye, center, and up, based on the camera's default mode.
     */
    private void setDefaultMode(GlobalState gs) {
        double dist_xy_proj = cos(gs.phi) * gs.vDist;
        center.x = 0;
        center.y = 0;
        center.z = 0;
        eye.x = cos(gs.theta) * dist_xy_proj;
        eye.y = sin(gs.theta) * dist_xy_proj;
        eye.z = gs.vDist * sin(gs.phi);
    }

    /**
     * Computes eye, center, and up, based on the first person mode.
     * The camera should view from the perspective of the robot.
     */
    private void setFirstPersonMode(GlobalState gs, Robot focus) {
        Vector dir = focus.direction.normalized();
        
        eye.x = focus.headPos.x + focus.position.x;
        eye.y = focus.headPos.y + focus.position.y;
        eye.z = focus.headPos.z + focus.headSize + focus.position.z;
        
        center.x = eye.x + dir.x;
        center.y = eye.y + dir.y;
        center.z = eye.z + dir.z;
    }
}
