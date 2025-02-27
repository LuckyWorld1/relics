package it.hurts.sskirillss.relics.utils;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

import java.awt.*;
import java.util.Random;

public class RenderUtils {
    public static void renderBeams(MatrixStack matrixStack, IRenderTypeBuffer bufferIn, float partialTicks, int amount, float size, Color color) {
        matrixStack.pushPose();

        Random random = new Random(1488);

        for (int i = 1; i < amount; ++i) {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F + partialTicks));
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F + partialTicks));
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F + partialTicks));

            renderBeam(matrixStack, bufferIn, partialTicks, size, color);
        }

        matrixStack.popPose();
    }

    public static void renderBeam(MatrixStack matrixStack, IRenderTypeBuffer bufferIn, float partialTicks, float size, Color color) {
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.lightning());
        Matrix4f matrix4f = matrixStack.last().pose();

        float length = size * 0.2F;

        int red = MathHelper.clamp(color.getRed(), 0, 255);
        int green = MathHelper.clamp(color.getGreen(), 0, 255);
        int blue = MathHelper.clamp(color.getBlue(), 0, 255);
        int alpha = (int) (255.0F * (1.0F - partialTicks / 200.0F));

        builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
        builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
        builder.vertex(matrix4f, -(float) (Math.sqrt(3.0D) / 2.0D) * length, size, -0.5F * length).color(red, green, blue, 0).endVertex();
        builder.vertex(matrix4f, (float) (Math.sqrt(3.0D) / 2.0D) * length, size, -0.5F * length).color(red, green, blue, 0).endVertex();
        builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
        builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
        builder.vertex(matrix4f, (float) (Math.sqrt(3.0D) / 2.0D) * length, size, -0.5F * length).color(red, green, blue, 0).endVertex();
        builder.vertex(matrix4f, 0.0F, size, length).color(red, green, blue, 0).endVertex();
        builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
        builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F).color(255, 255, 255, alpha).endVertex();
        builder.vertex(matrix4f, 0.0F, size, length).color(red, green, blue, 0).endVertex();
        builder.vertex(matrix4f, -(float) (Math.sqrt(3.0D) / 2.0D) * length, size, -0.5F * length).color(red, green, blue, 0).endVertex();
    }

    public static void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}