// simple vertex shader
varying vec3 R; 
varying vec3 N; // to be used in fragment shader 
varying vec3 P, L; //Position of vertex in eyespace

void main()
{
    gl_LightSourceParameters light = gl_LightSource[0];
    gl_MaterialParameters mat = gl_FrontMaterial;

    vec3 V = (gl_ModelViewMatrixInverse * vec4(0.0, 0.0, 0.0, 1.0)).xyz;// Transform view position from eye space to model space
    N = normalize(gl_NormalMatrix * gl_Normal);			// Normal of vertex in model space
    P = vec3(gl_ModelViewMatrix * gl_Vertex); 			// Vertex position in model space
    L = normalize(light.position.xyz - P);
    R = normalize(2.0 * dot(N, L)*N - L); 					// compute reflection vector in model space 

    // vertex shader output 
    gl_TexCoord[0] = gl_MultiTexCoord0; 
    gl_Position    = gl_ModelViewProjectionMatrix * gl_Vertex;      // model view transform
}

