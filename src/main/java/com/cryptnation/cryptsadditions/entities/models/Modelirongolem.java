package com.cryptnation.cryptsadditions.entities.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class Modelirongolem extends EntityModel<Entity> {
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer arm0;
    private final ModelRenderer arm1;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;

    public Modelirongolem() {
        textureWidth = 128;
        textureHeight = 128;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -16.0F, 0.0F);
        body.setTextureOffset(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18.0F, 12.0F, 11.0F, 0.0F, false);
        body.setTextureOffset(0, 70).addBox(-4.5F, 10.0F, -5.0F, 9.0F, 5.0F, 8.0F, 0.5F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, -2.0F);
        body.addChild(head);
        head.setTextureOffset(0, 0).addBox(-3.0F, -9.0F, -3.5F, 6.0F, 7.0F, 8.0F, 0.0F, false);

        arm0 = new ModelRenderer(this);
        arm0.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(arm0);
        arm0.setTextureOffset(83, 0).addBox(-13.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F, false);

        arm1 = new ModelRenderer(this);
        arm1.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(arm1);
        arm1.setTextureOffset(60, 58).addBox(9.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F, false);

        leg0 = new ModelRenderer(this);
        leg0.setRotationPoint(-4.0F, 18.0F, 0.0F);
        body.addChild(leg0);
        leg0.setTextureOffset(37, 0).addBox(-3.5F, -3.0F, -3.0F, 6.0F, 25.0F, 5.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(5.0F, 18.0F, 0.0F);
        body.addChild(leg1);
        leg1.setTextureOffset(60, 0).addBox(-3.5F, -3.0F, -3.0F, 6.0F, 25.0F, 5.0F, 0.0F, true);
    }

    @Override
    public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
                       float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.leg0.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
        this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
        this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.arm0.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
    }
}