package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.effects.SealEffect;
import FrierenMod.powers.SealPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class SealCardsAction extends AbstractGameAction {
    private final AbstractPlayer p = AbstractDungeon.player;
    @Override
    public void update() {
        ArrayList<CardGroup> groups = initManipulateCardGroups();
        ArrayList<AbstractCard> sealCards = new ArrayList<>();
        for(CardGroup group:groups){
            this.storeSealCardsInCardGroup(sealCards,group);
        }
        if(!sealCards.isEmpty()){
            for(AbstractCard c:sealCards){
                for(CardGroup group:groups){
                    this.addToBot(new DestroySpecifiedCardAction(c,group,true));
                    this.addToBot(new VFXAction(new SealEffect(c)));
                }
            }
            this.addToBot(new ApplyPowerAction(p,p,new SealPower(p,sealCards),1));
        }
        this.isDone = true;
    }
    private void storeSealCardsInCardGroup(ArrayList<AbstractCard> sealCards, CardGroup cardGroup){
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isSealCard){
                sealCards.add(c);
            }
        }
    }
    private ArrayList<CardGroup> initManipulateCardGroups(){
        ArrayList<CardGroup> groups = new ArrayList<>();
        groups.add(p.drawPile);
        groups.add(p.hand);
        groups.add(p.discardPile);
        return groups;
    }
}
