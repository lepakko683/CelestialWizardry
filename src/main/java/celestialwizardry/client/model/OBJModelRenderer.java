package celestialwizardry.client.model;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import celestialwizardry.util.Loc3DF;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.model.obj.GroupObject;

// Minecraft class ModelRenderer.class used as reference
@SuppressWarnings({"rawtypes", "unchecked"})
public class OBJModelRenderer {
	/** The size of the texture file's width in pixels. */
    public float textureWidth;
    /** The size of the texture file's height in pixels. */
    public float textureHeight;
    /** The X offset into the texture used for displaying this model */
    private int textureOffsetX;
    /** The Y offset into the texture used for displaying this model */
    private int textureOffsetY;
    public Loc3DF rotPoint;
    public float rotateAngleX;
    public float rotateAngleY;
    public float rotateAngleZ;
    private boolean compiled;
    /** The GL display list rendered by the Tessellator for this model */
    private int displayList;
    public boolean mirror;
    public boolean showModel;
    /** Hides the model. */
    public boolean isHidden;
    public List objList;
    public List childModels;
    public final String boxName;
    private ModelBase baseModel;
    public float offsetX;
    public float offsetY;
    public float offsetZ;
    
    public OBJModelRenderer(ModelBase mbase, String name) {
    	this.textureWidth = 64.0F;
        this.textureHeight = 32.0F;
        this.showModel = true;
        this.objList = new ArrayList();
        this.baseModel = mbase;
        mbase.boxList.add(this);
        this.boxName = name;
        this.setTextureSize(mbase.textureWidth, mbase.textureHeight);
    }
    
    public OBJModelRenderer(ModelBase mbase)
    {
        this(mbase, (String)null);
    }

    public OBJModelRenderer(ModelBase mbase, int texOffset_x, int texOffset_y)
    {
        this(mbase);
        this.setTextureOffset(texOffset_x, texOffset_y);
    }
    
    public OBJModelRenderer setTextureOffset(int par1, int par2)
    {
        this.textureOffsetX = par1;
        this.textureOffsetY = par2;
        return this;
    }
    
    public void setRotationPoint(float x, float y, float z)
    {
    	this.rotPoint = new Loc3DF(x, y, z);
    }
    
    public void setRotationPoint(Loc3DF pt)
    {
    	this.rotPoint = pt;
    }
    
	public void addGroupObject(GroupObject go)
    {
        this.objList.add(go);
    }
    
    /**
     * Compiles a GL display list for this model
     */
    @SideOnly(Side.CLIENT)
    private void compileDisplayList(float partTick)
    {
        this.displayList = GLAllocation.generateDisplayLists(1);
        
        GL11.glNewList(this.displayList, GL11.GL_COMPILE);
        Tessellator tessellator = Tessellator.instance;

        for (int i = 0; i < this.objList.size(); ++i)
        {
            ((GroupObject)this.objList.get(i)).render(tessellator/*, partTick*/);
        }

        GL11.glEndList();
        this.compiled = true;
    }
    
    public OBJModelRenderer setTextureSize(int width, int height)
    {
        this.textureWidth = (float)width;
        this.textureHeight = (float)height;
        return this;
    }
    
    @SideOnly(Side.CLIENT)
    public void render(float partTick)
    {
        if (!this.isHidden)
        {
            if (this.showModel)
            {
                if (!this.compiled)
                {
                    this.compileDisplayList(partTick);
                }

                GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
                int i;

                if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F)
                {
                    if (this.rotPoint.getX() == 0.0F && this.rotPoint.getY() == 0.0F && this.rotPoint.getZ() == 0.0F)
                    {
                        GL11.glCallList(this.displayList);

                        if (this.childModels != null)
                        {
                            for (i = 0; i < this.childModels.size(); ++i)
                            {
                                ((GroupObject)this.childModels.get(i)).render();
                            }
                        }
                    }
                    else
                    {
                        GL11.glTranslatef(this.rotPoint.getX() * partTick, this.rotPoint.getY() * partTick, this.rotPoint.getZ() * partTick);
                        GL11.glCallList(this.displayList);

                        if (this.childModels != null)
                        {
                            for (i = 0; i < this.childModels.size(); ++i)
                            {
                            	((GroupObject)this.childModels.get(i)).render();
                            }
                        }

                        GL11.glTranslatef(-this.rotPoint.getX() * partTick, -this.rotPoint.getY() * partTick, -this.rotPoint.getZ() * partTick);
                    }
                }
                else
                {
                    GL11.glPushMatrix();
                    GL11.glTranslatef(this.rotPoint.getX() * partTick, this.rotPoint.getY() * partTick, this.rotPoint.getZ() * partTick);

                    if (this.rotateAngleZ != 0.0F)
                    {
                        GL11.glRotatef(this.rotateAngleZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
                    }

                    if (this.rotateAngleY != 0.0F)
                    {
                        GL11.glRotatef(this.rotateAngleY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
                    }

                    if (this.rotateAngleX != 0.0F)
                    {
                        GL11.glRotatef(this.rotateAngleX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
                    }

                    GL11.glCallList(this.displayList);

                    if (this.childModels != null)
                    {
                        for (i = 0; i < this.childModels.size(); ++i)
                        {
                        	((GroupObject)this.childModels.get(i)).render();
                        }
                    }

                    GL11.glPopMatrix();
                }

                GL11.glTranslatef(-this.offsetX, -this.offsetY, -this.offsetZ);
            }
        }
    }
    
    
    
    @SideOnly(Side.CLIENT)
    public void renderWithRotation(float par1)
    {
        if (!this.isHidden)
        {
            if (this.showModel)
            {
                if (!this.compiled)
                {
                    this.compileDisplayList(par1);
                }

                GL11.glPushMatrix();
                GL11.glTranslatef(this.rotPoint.getX() * par1, this.rotPoint.getY() * par1, this.rotPoint.getZ() * par1);

                if (this.rotateAngleY != 0.0F)
                {
                    GL11.glRotatef(this.rotateAngleY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
                }

                if (this.rotateAngleX != 0.0F)
                {
                    GL11.glRotatef(this.rotateAngleX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
                }

                if (this.rotateAngleZ != 0.0F)
                {
                    GL11.glRotatef(this.rotateAngleZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
                }

                GL11.glCallList(this.displayList);
                GL11.glPopMatrix();
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void postRender(float par1)
    {
        if (!this.isHidden)
        {
            if (this.showModel)
            {
                if (!this.compiled)
                {
                    this.compileDisplayList(par1);
                }

                if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F)
                {
                    if (this.rotPoint.getX() != 0.0F || this.rotPoint.getY() != 0.0F || this.rotPoint.getZ() != 0.0F)
                    {
                        GL11.glTranslatef(this.rotPoint.getX() * par1, this.rotPoint.getY() * par1, this.rotPoint.getZ() * par1);
                    }
                }
                else
                {
                    GL11.glTranslatef(this.rotPoint.getX() * par1, this.rotPoint.getY() * par1, this.rotPoint.getZ() * par1);

                    if (this.rotateAngleZ != 0.0F)
                    {
                        GL11.glRotatef(this.rotateAngleZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
                    }

                    if (this.rotateAngleY != 0.0F)
                    {
                        GL11.glRotatef(this.rotateAngleY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
                    }

                    if (this.rotateAngleX != 0.0F)
                    {
                        GL11.glRotatef(this.rotateAngleX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
                    }
                }
            }
        }
    }
}
