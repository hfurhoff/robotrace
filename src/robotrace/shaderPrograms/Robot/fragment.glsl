#version 120

varying vec3 N; 					// fragment normal in eye space. 
varying vec4 P; 					//vertex position in eyespace

vec4 shading(vec3 p, vec3 BN, gl_LightSourceParameters light, gl_MaterialParameters mat) { // compute phong shader
	vec4 result = vec4(0,0,0,1); // opaque black 

	vec3 L = normalize(light.position.xyz - p); // vector towards light source 
	result += vec4(	light.diffuse.x*mat.diffuse.x, 
					light.diffuse.y*mat.diffuse.y, 
					light.diffuse.z*mat.diffuse.z, 
					light.diffuse.w*mat.diffuse.w
					) * dot(BN, L); // compute diffuse contribution 

	vec3 E = (gl_ProjectionMatrixInverse * vec4(0.0, 0.0, 0.0, 1.0)).xyz; // position of camera in View space 
	vec3 V = normalize(p - E); // direction towards viewer 
	result += vec4(	light.specular.x*mat.specular.x, 
					light.specular.y*mat.specular.y, 
					light.specular.z*mat.specular.z, 
					light.specular.w*mat.specular.w
	) * max(0.0, sign(dot(reflect(L, BN), V)) * 
			pow(dot(reflect(L, BN), V), mat.shininess)); // compute specular contribution 
	
	return result; 
} 
void main() { 
	gl_LightSourceParameters light = gl_LightSource[0]; 
	gl_MaterialParameters mat = gl_FrontMaterial; 		
	vec3 bumpedNormal = N; 								
	vec3 position = P.xyz;								
	
	gl_FragColor = shading(position, bumpedNormal, light, mat); 
}