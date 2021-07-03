package com.cryptnation.cryptsadditions.entities.renderer;

import com.cryptnation.cryptsadditions.entities.StoneGuardianEntity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.cryptnation.cryptsadditions.entities.StoneGuardianEntity;

@OnlyIn(Dist.CLIENT)
public class StoneGuardianRenderer {
    public static class ModelRegisterHandler {
        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public void registerModels(ModelRegistryEvent event) {
            RenderingRegistry.registerEntityRenderingHandler(StoneGuardianEntity.entity, renderManager -> {
                return new MobRenderer(renderManager, new Modelirongolem(), 1f) {
                    {
                        this.addLayer(new GlowingLayer<>(this));
                    }
                    @Override
                    public ResourceLocation getEntityTexture(Entity entity) {
                        return new ResourceLocation("crypts_additions:textures/guardian_golem.png");
                    }
                };
            });
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
        public GlowingLayer(IEntityRenderer<T, M> er) {
            super(er);
        }

        public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
                           float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            IVertexBuilder ivertexbuilder = bufferIn
                    .getBuffer(RenderType.getEyes(new ResourceLocation("crypts_additions:textures/guardian_golem.png")));
            this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
    }

    // Made with Blockbench 3.8.4
    // Exported for Minecraft version 1.15 - 1.16
    // Paste this class into your mod and generate all required imports
    public static class Modelirongolem extends EntityModel<Entity> {
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
        public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                           float alpha) {
            body.render(matrixStack, buffer, packedLight, packedOverlay);
        }

        public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
            modelRenderer.rotateAngleX = x;
            modelRenderer.rotateAngleY = y;
            modelRenderer.rotateAngleZ = z;
        }

        public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
            this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
            this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
            this.leg0.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
            this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
            this.arm1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
            this.arm0.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        }
    }
}
