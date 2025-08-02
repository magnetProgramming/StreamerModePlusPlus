package com.magnet.streamermodeplusplus.mixins;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.magnet.streamermodeplusplus.StreamerModePlusPlus;
import com.magnet.streamermodeplusplus.Events.EventDebugHud;

import net.minecraft.client.gui.hud.DebugHud;

@Mixin(DebugHud.class)
public class MixinDebugHud {

    @Inject(method = "getLeftText", at = @At("RETURN"), cancellable = true)
    private void onGetLeftText(CallbackInfoReturnable<List<String>> cir) {
        List<String> lines = new ArrayList<>(cir.getReturnValue());

        cir.setReturnValue(lines); 
        
        try 
        {
        	StreamerModePlusPlus.eventBus.post(new EventDebugHud(lines));
        	System.out.println("Success");
        } catch (Exception ex) 
        {
        	System.out.println(ex.toString());
        }
    }
}
