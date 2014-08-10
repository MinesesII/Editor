package Editor;

import com.jme3.math.Vector3f;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.shape.AbstractBox;
import com.jme3.util.BufferUtils;

import java.io.Serializable;
import java.nio.FloatBuffer;

@SuppressWarnings("serial")
public class Box extends AbstractBox implements Serializable
{
	
	private float xsize;
	private float ysize;
	private float zsize;
	private float posx;
	private float posy;
	private float posz;
	private String texture;
    
    private transient static final short[] GEOMETRY_INDICES_DATA = {
         2,  1,  0,  3,  2,  0, // back
         6,  5,  4,  7,  6,  4, // right
        10,  9,  8, 11, 10,  8, // front
        14, 13, 12, 15, 14, 12, // left
        18, 17, 16, 19, 18, 16, // top
        22, 21, 20, 23, 22, 20  // bottom
    };

    private transient static final float[] GEOMETRY_NORMALS_DATA = {
        0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1, // back
        1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0, // right
        0,  0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1, // front
       -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0,  0, // left
        0,  1,  0,  0,  1,  0,  0,  1,  0,  0,  1,  0, // top
        0, -1,  0,  0, -1,  0,  0, -1,  0,  0, -1,  0  // bottom
    };

    private transient static final float[] GEOMETRY_TEXTURE_DATA = {
        1, 0, 0, 0, 0, 1, 1, 1, // back
        1, 0, 0, 0, 0, 1, 1, 1, // right
        1, 0, 0, 0, 0, 1, 1, 1, // front
        1, 0, 0, 0, 0, 1, 1, 1, // left
        1, 0, 0, 0, 0, 1, 1, 1, // top
        1, 0, 0, 0, 0, 1, 1, 1  // bottom
    };

    public Box(float x, float y, float z, float Posx, float Posy, float Posz, String texturename) 
    {
        super();
        updateGeometry(Vector3f.ZERO, x, y, z);
        xsize = x;
        ysize = y;
        zsize = z;
        posx = Posx;
        posy = Posy;
        posz = Posz;
        texture = texturename; 
    }

    @Deprecated
    public Box(Vector3f center, float x, float y, float z) 
    {
        super();
        updateGeometry(center, x, y, z);
    }

    public Box(Vector3f min, Vector3f max) 
    {
        super();
        updateGeometry(min, max);
    }

    public Box()
    {
        super();
    }

    @Override
    public Box clone() 
    {
        return new Box(center.clone(), xExtent, yExtent, zExtent);
    }

    protected void duUpdateGeometryIndices() 
    {
        if (getBuffer(Type.Index) == null){
            setBuffer(Type.Index, 3, BufferUtils.createShortBuffer(GEOMETRY_INDICES_DATA));
        }
    }

    protected void duUpdateGeometryNormals() 
    {
        if (getBuffer(Type.Normal) == null){
            setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(GEOMETRY_NORMALS_DATA));
        }
    }

    protected void duUpdateGeometryTextures() 
    {
        if (getBuffer(Type.TexCoord) == null)
        {
            setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(GEOMETRY_TEXTURE_DATA));
        }
    }

    protected void duUpdateGeometryVertices() 
    {
        FloatBuffer fpb = BufferUtils.createVector3Buffer(24);
        Vector3f[] v = computeVertices();
        fpb.put(new float[] {
                v[0].x, v[0].y, v[0].z, v[1].x, v[1].y, v[1].z, v[2].x, v[2].y, v[2].z, v[3].x, v[3].y, v[3].z, // back
                v[1].x, v[1].y, v[1].z, v[4].x, v[4].y, v[4].z, v[6].x, v[6].y, v[6].z, v[2].x, v[2].y, v[2].z, // right
                v[4].x, v[4].y, v[4].z, v[5].x, v[5].y, v[5].z, v[7].x, v[7].y, v[7].z, v[6].x, v[6].y, v[6].z, // front
                v[5].x, v[5].y, v[5].z, v[0].x, v[0].y, v[0].z, v[3].x, v[3].y, v[3].z, v[7].x, v[7].y, v[7].z, // left
                v[2].x, v[2].y, v[2].z, v[6].x, v[6].y, v[6].z, v[7].x, v[7].y, v[7].z, v[3].x, v[3].y, v[3].z, // top
                v[0].x, v[0].y, v[0].z, v[5].x, v[5].y, v[5].z, v[4].x, v[4].y, v[4].z, v[1].x, v[1].y, v[1].z  // bottom
        });
        setBuffer(Type.Position, 3, fpb);
        updateBound();
    }
    
    public float getSizeX()
    {
    	return xsize;
    }
    
    public float getSizeY()
    {
    	return ysize;
    }
    
    public float getSizeZ()
    {
    	return zsize;
    }
    
    public float getPosX()
    {
    	return posx;
    }
    
    public float getPosY()
    {
    	return posy;
    }
    
    public float getPosZ()
    {
    	return posz;
    }
    
    public String getTextureName()
    {
    	return texture;
    }

}