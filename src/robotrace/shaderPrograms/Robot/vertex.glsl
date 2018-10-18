#version 120

varying vec3 N; // to be used in fragment shader 
varying vec4 P; //Position of vertex in eyespace
void main( void ) { 
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex; 
	gl_TexCoord[0] = gl_MultiTexCoord0; 
	gl_FrontColor = gl_Color; 
	N = normalize(gl_NormalMatrix * gl_Normal); // Normalvector to vertex
	P = gl_ModelViewMatrix * gl_Vertex;
}