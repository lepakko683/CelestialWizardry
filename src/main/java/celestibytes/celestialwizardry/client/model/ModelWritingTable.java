// Date: 24.5.2014 19:03:47
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX


package celestibytes.celestialwizardry.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWritingTable extends ModelBase
{
    //fields
    ModelRenderer frontpanel;
    ModelRenderer backpanel;
    ModelRenderer leftpanel;
    ModelRenderer rightpanel;
    ModelRenderer diagonalsurface;
    ModelRenderer bottompanel;

    public ModelWritingTable()
    {
        textureWidth = 256;
        textureHeight = 32;

        frontpanel = new ModelRenderer(this, 122, 0);
        frontpanel.addBox(0F, 0F, 0F, 16, 12, 1);
        frontpanel.setRotationPoint(-8F, 12F, -8F);
        frontpanel.setTextureSize(256, 32);
        frontpanel.mirror = true;
        setRotation(frontpanel, 0F, 0F, 0F);
        backpanel = new ModelRenderer(this, 216, 0);
        backpanel.addBox(0F, 0F, 0F, 16, 16, 4);
        backpanel.setRotationPoint(-8F, 8F, 4F);
        backpanel.setTextureSize(256, 32);
        backpanel.mirror = true;
        setRotation(backpanel, 0F, 0F, 0F);
        leftpanel = new ModelRenderer(this, 24, 0);
        leftpanel.addBox(0F, 0F, 0F, 1, 11, 11);
        leftpanel.setRotationPoint(-8F, 13F, -7F);
        leftpanel.setTextureSize(256, 32);
        leftpanel.mirror = true;
        setRotation(leftpanel, 0F, 0F, 0F);
        rightpanel = new ModelRenderer(this, 0, 0);
        rightpanel.addBox(0F, 0F, 0F, 1, 11, 11);
        rightpanel.setRotationPoint(7F, 13F, -7F);
        rightpanel.setTextureSize(256, 32);
        rightpanel.mirror = true;
        setRotation(rightpanel, 0F, 0F, 0F);
        diagonalsurface = new ModelRenderer(this, 157, 0);
        diagonalsurface.addBox(0F, 0F, 0F, 16, 5, 13);
        diagonalsurface.setRotationPoint(-8F, 13F, -8F);
        diagonalsurface.setTextureSize(256, 32);
        diagonalsurface.mirror = true;
        setRotation(diagonalsurface, 0.3950029F, 0F, 0F);
        bottompanel = new ModelRenderer(this, 65, 0);
        bottompanel.addBox(0F, 0F, 0F, 14, 1, 14);
        bottompanel.setRotationPoint(-7F, 23F, -7F);
        bottompanel.setTextureSize(256, 32);
        bottompanel.mirror = true;
        setRotation(bottompanel, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        frontpanel.render(f5);
        backpanel.render(f5);
        leftpanel.render(f5);
        rightpanel.render(f5);
        diagonalsurface.render(f5);
        bottompanel.render(f5);
    }

    public void render2(float f5)
    {
        frontpanel.render(f5);
        backpanel.render(f5);
        leftpanel.render(f5);
        rightpanel.render(f5);
        diagonalsurface.render(f5);
        bottompanel.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
    }

}
