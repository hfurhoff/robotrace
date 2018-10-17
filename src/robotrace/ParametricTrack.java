
package robotrace;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.PI;

/**
 * Implementation of RaceTrack, creating a track from a parametric formula
 */
public class ParametricTrack extends RaceTrack {
    
    @Override
    protected Vector getPoint(double t) {
        // Return point according to function P(t) = (10cos(2t), 14sin(2t), 1)
        return new Vector(10*cos(2*PI*t), 14*sin(2*PI*t), 1);
    }

    @Override
    protected Vector getTangent(double t) {
        // Return tangent according to function P'(t) = (-20sin(2t), 28cos(2t), 0)
        return new Vector(-20*sin(2*PI*t), 28*cos(2*PI*t), 0);
    }
    
}
