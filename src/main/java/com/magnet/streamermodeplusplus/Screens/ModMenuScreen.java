package com.magnet.streamermodeplusplus.Screens;

import com.magnet.streamermodeplusplus.StreamerModePlusPlusClient;

import dev.lambdaurora.spruceui.Position;
import dev.lambdaurora.spruceui.SpruceTextAlignment;
import dev.lambdaurora.spruceui.option.SpruceCheckboxBooleanOption;
import dev.lambdaurora.spruceui.tooltip.TooltipData;
import dev.lambdaurora.spruceui.widget.SpruceButtonWidget;
import dev.lambdaurora.spruceui.widget.SpruceLabelWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceContainerWidget;
import dev.lambdaurora.spruceui.widget.container.SpruceOptionListWidget;
import dev.lambdaurora.spruceui.widget.text.SpruceTextFieldWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModMenuScreen extends Screen {

    private SpruceContainerWidget container;
    
    public ModMenuScreen() {
        super(Text.of("Mod Menu"));
    }

    @Override
    protected void init() {
        super.init();

        container = new SpruceContainerWidget(Position.of(0, 0), this.width, this.height);
        container.setBackground((graphics, widget, vOffset, mouseX, mouseY, delta) -> {
            int x = widget.getX();
            int y = widget.getY();
            int width = widget.getWidth();
            graphics.fill(x, y, x + width, y + widget.getHeight(), 0x88000000);
        });

        SpruceLabelWidget label = new SpruceLabelWidget(
            Position.of(this.width / 2 - 100, 10),
            Text.of("StreamerMode++"),
            200,
            lbl -> {},
            SpruceTextAlignment.CENTER
        );
        container.addChild(label);

        SpruceOptionListWidget optionList = new SpruceOptionListWidget(
            Position.of(20, 40),
            this.width - 40,
            80
        );

        optionList.addOptionEntry(
            new SpruceCheckboxBooleanOption(
                "key.streamermodeplusplus.toggle_blocked_F3_coords_option",
                () -> StreamerModePlusPlusClient.mixinHideCoordsDebugHudEnabled,
                newValue -> StreamerModePlusPlusClient.mixinHideCoordsDebugHudEnabled = newValue,
                TooltipData.builder().text("Toggle hiding F3 coordinates").build()
            ),
            new SpruceCheckboxBooleanOption(
                "key.streamermodeplusplus.toggle_hide_F3_block_info_option",
                () -> StreamerModePlusPlusClient.mixinHideBlockInfoDebugHudEnabled,
                newValue -> StreamerModePlusPlusClient.mixinHideBlockInfoDebugHudEnabled = newValue,
                TooltipData.builder().text("Toggle hiding F3 block info").build()
            )
        );

        optionList.addOptionEntry(
            new SpruceCheckboxBooleanOption(
                "key.streamermodeplusplus.toggle_hide_F3_chunk_info_option",
                () -> StreamerModePlusPlusClient.mixinHideChunkInfoDebugHudEnabled,
                newValue -> StreamerModePlusPlusClient.mixinHideChunkInfoDebugHudEnabled = newValue,
                TooltipData.builder().text("Toggle hiding F3 chunk info").build()
            ),
            new SpruceCheckboxBooleanOption(
                "key.streamermodeplusplus.toggle_hide_F3_face_info_option",
                () -> StreamerModePlusPlusClient.mixinHideFaceInfoDebugHudEnabled,
                newValue -> StreamerModePlusPlusClient.mixinHideFaceInfoDebugHudEnabled = newValue,
                TooltipData.builder().text("Toggle hiding F3 face info").build()
            )
        );

        optionList.addOptionEntry(
            new SpruceCheckboxBooleanOption(
                "key.streamermodeplusplus.toggle_hide_F3_targeted_block_info_option",
                () -> StreamerModePlusPlusClient.mixinHideTargetedBlockDebugHudEnabled,
                newValue -> StreamerModePlusPlusClient.mixinHideTargetedBlockDebugHudEnabled = newValue,
                TooltipData.builder().text("Toggle hiding F3 Targeted Block info").build()
            ),
            new SpruceCheckboxBooleanOption(
                "key.streamermodeplusplus.toggle_hide_F3_targeted_fluid_info_option",
                () -> StreamerModePlusPlusClient.mixinHideTargetedFluidDebugHudEnabled,
                newValue -> StreamerModePlusPlusClient.mixinHideTargetedFluidDebugHudEnabled = newValue,
                TooltipData.builder().text("Toggle hiding F3 Targeted Fluid info").build()
            )
        );

        container.addChild(optionList);

        int groupStartX = this.width / 2 - 90;
        int groupStartY = 130;
        int fieldWidth = 180;
        int fieldHeight = 12;
        int fieldSpacing = 13;

        SpruceLabelWidget hiddenFieldsLabel = new SpruceLabelWidget(
            Position.of(groupStartX, groupStartY),
            Text.of("Customize Hidden Text Overrides:"),
            fieldWidth,
            lbl -> {},
            SpruceTextAlignment.LEFT
        );
        container.addChild(hiddenFieldsLabel);

        var client = StreamerModePlusPlusClient.getInstance();
        int fieldX = groupStartX + 10;
        int baseY = groupStartY + 15;

        SpruceTextFieldWidget coordField = SpruceTextFieldWidget.builder(
            Position.of(fieldX, baseY),
            fieldWidth - 20, fieldHeight
        ).title(Text.of("Coord Text")).placeholder(Text.of("Coord Text")).build();
        coordField.setText(client.getHiddenCoordsMessage());
        addDrawableChild(coordField);

        SpruceTextFieldWidget blockField = SpruceTextFieldWidget.builder(
            Position.of(fieldX, baseY + fieldSpacing * 1),
            fieldWidth - 20, fieldHeight
        ).title(Text.of("Block Info Text")).placeholder(Text.of("Block Info Text")).build();
        blockField.setText(client.getHiddenBlockMessage());
        addDrawableChild(blockField);

        SpruceTextFieldWidget chunkField = SpruceTextFieldWidget.builder(
            Position.of(fieldX, baseY + fieldSpacing * 2),
            fieldWidth - 20, fieldHeight
        ).title(Text.of("Chunk Info Text")).placeholder(Text.of("Chunk Info Text")).build();
        chunkField.setText(client.getHiddenChunkMessage());
        addDrawableChild(chunkField);

        SpruceTextFieldWidget faceField = SpruceTextFieldWidget.builder(
            Position.of(fieldX, baseY + fieldSpacing * 3),
            fieldWidth - 20, fieldHeight
        ).title(Text.of("Facing Info Text")).placeholder(Text.of("Facing Info Text")).build();
        faceField.setText(client.getHiddenFaceMessage());
        addDrawableChild(faceField);

        SpruceTextFieldWidget targetedBlockField = SpruceTextFieldWidget.builder(
            Position.of(fieldX, baseY + fieldSpacing * 4),
            fieldWidth - 20, fieldHeight
        ).title(Text.of("Targeted Block Info Text")).placeholder(Text.of("Targeted Block Info Text")).build();
        targetedBlockField.setText(client.getHiddenTargetedBlockMessage());
        addDrawableChild(targetedBlockField);

        SpruceTextFieldWidget targetedFluidField = SpruceTextFieldWidget.builder(
            Position.of(fieldX, baseY + fieldSpacing * 5),
            fieldWidth - 20, fieldHeight
        ).title(Text.of("Targeted Fluid Info Text")).placeholder(Text.of("Targeted Fluid Info Text")).build();
        targetedFluidField.setText(client.getHiddenTargetedFluidMessage());
        addDrawableChild(targetedFluidField);

        this.addDrawableChild(new SpruceButtonWidget(
            Position.of(groupStartX, baseY + fieldSpacing * 6 + 3),
            fieldWidth,
            16,
            Text.of("Submit"),
            btn -> {
                client.setHiddenCoordsMessage(coordField.getText());
                client.setHiddenBlockMessage(blockField.getText());
                client.setHiddenChunkMessage(chunkField.getText());
                client.setHiddenFaceMessage(faceField.getText());
                client.setHiddenTargetedBlockMessage(targetedBlockField.getText());
                client.setHiddenTargetedFluidMessage(targetedFluidField.getText());
            }
        ));

        this.addDrawableChild(container);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderInGameBackground(context);

        container.render(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
    }

    public void renderInGameBackground(DrawContext context) {
        context.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return container.mouseClicked(mouseX, mouseY, button) || super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void close() {
        super.close();
    }
}