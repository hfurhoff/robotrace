
package robotrace;

/**
 * Implementation of RaceTrack, creating a track from control points for a 
 * cubic Bezier curve
 */
public class BezierTrack extends RaceTrack {
    
    private Vector[] controlPoints;
    private double segmentLen;
    private int segmentNum;

    BezierTrack(Vector[] controlPoints) {
        this.controlPoints = controlPoints;
        this.segmentNum = (int)(controlPoints.length / 4);  // Set segment number for cubic bezier
        this.segmentLen = 1.0d / ((double)segmentNum);      // Set segment length
    }
    
    @Override
    protected Vector getPoint(double t) {
        t = t % 1;
        int segment = (int)Math.floor(t/segmentLen);        // Define in which segment the point is 
        t = (t - segment * segmentLen) / segmentLen;        // Set point inside segment
        
        // Set Bezier Points
        Vector P0 = controlPoints[4*segment];
        Vector P1 = controlPoints[4*segment+1];
        Vector P2 = controlPoints[4*segment+2];
        Vector P3 = controlPoints[4*segment+3];

        return getCubicBezierPnt(t, P0, P1, P2, P3);

    }

    @Override
    protected Vector getTangent(double t) {
        t = t % 1;
        int segment = (int)Math.floor(t/segmentLen);        // Define in which segment the point is 
        t = (t - segment * segmentLen) / segmentLen;        // Set point inside segment
        
        // Set Bezier Points
        Vector P0 = controlPoints[4*segment];
        Vector P1 = controlPoints[4*segment+1];
        Vector P2 = controlPoints[4*segment+2];
        Vector P3 = controlPoints[4*segment+3];

        return getCubicBezierTng(t, P0, P1, P2, P3);

    }
    

}
