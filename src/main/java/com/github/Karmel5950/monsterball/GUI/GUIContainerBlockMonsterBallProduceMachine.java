package com.github.Karmel5950.monsterball.GUI;

import com.github.Karmel5950.monsterball.MonsterBall;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GUIContainerBlockMonsterBallProduceMachine extends GuiContainer {
    private static final String TEXTURE_PATH = MonsterBall.MODID + ":" + "textures/gui/gui_produce_machine.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    protected ContainerBlockMonsterBallProduceMachine contanier;

    public GUIContainerBlockMonsterBallProduceMachine(Container contanier) {
        super(contanier);
        this.xSize = 175;
        this.ySize = 165;
        this.contanier =(ContainerBlockMonsterBallProduceMachine)contanier;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        int burnTime = this.contanier.getBurnTime();
        int maxBurnTime = this.contanier.getMaxBurnTime();
        int burnTimetextureHeight = 1 + (int) Math.ceil(13.0 * burnTime / maxBurnTime);
        this.drawTexturedModalRect(offsetX + 55, offsetY + 51 - burnTimetextureHeight, 176, 14 - burnTimetextureHeight , 14, burnTimetextureHeight);

        int burnProcess = this.contanier.getBurnProcess();
        int burnTagetProcess = this.contanier.getBurnTagetProcess();
        int ProcesstextureWidth = 1 + (int) Math.ceil(22.0 * burnProcess / burnTagetProcess);
        this.drawTexturedModalRect(offsetX + 79, offsetY + 35, 176, 14, ProcesstextureWidth, 17);
    }
}
