
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
           
        // P(t) = (1 - t)^3 * P0 + 3t(1-t)^2 * P1 + 3t^2 (1-t) * P2 + t^3 * P3
        return P0.scale(Math.pow(1 - t, 3)).add(P1.scale(3 * t * Math.pow(1 - t, 2)))
                .add(P2.scale(3 * Math.pow(t, 2) * (1 - t))).add(P3.scale(Math.pow(t, 3)));

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
           
        // P'(t) =  -3(1-t)^2 * P0 + 3(1-t)^2 * P1 - 6t(1-t) * P1 - 3t^2 * P2 + 6t(1-t) * P2 + 3t^2 * P3
        return P0.scale(-3 * Math.pow(1 - t, 2)).add(P1.scale(3 * Math.pow(1 - t, 2) - 6 * t * (1 - t)))
            .add(P2.scale(-3 * Math.pow(t, 2) + 6 * t * (1 - t))).add(P3.scale(3 * Math.pow(t, 2)));

    }
    

}
