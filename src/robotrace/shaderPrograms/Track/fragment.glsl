uniform sampler2D tex_track; // pick up texture map installed in ShaderMaker 
varying vec3 R; // vector from object vertex to eye reflected in the normal 
varying vec3 N; // to be used in fragment shader 
varying vec3 P; //Position of vertex in eyespace

vec4 shading(vec3 p, vec3 n, gl_LightSourceParameters light, gl_MaterialParameters mat) { // compute phong shader
	vec4 result = vec4(0,0,0,1); // opaque black 

	vec3 L = normalize(light.position.xyz - p); // vector towards light source 
	result += vec4(	light.diffuse.x*mat.diffuse.x, 
					light.diffuse.y*mat.diffuse.y, 
					light.diffuse.z*mat.diffuse.z, 
					light.diffuse.w*mat.diffuse.w
					) * dot(n, L); // compute diffuse contribution 

	vec3 E = (gl_ProjectionMatrixInverse * vec4(0.0, 0.0, 0.0, 1.0)).xyz; // position of camera in View space 
	vec3 V = normalize(E - p); // direction towards viewer 
	result += light.specular * pow(max(dot(R, V), 0.0), mat.shininess); // compute specular contribution 
	
	return result; 
} 


void main() { 
        gl_LightSourceParameters light = gl_LightSource[0]; 
	gl_MaterialParameters mat = gl_FrontMaterial; 
	gl_FragColor = texture2D(tex_track, gl_TexCoord[0].st) + shading(P, N, light, mat);
}