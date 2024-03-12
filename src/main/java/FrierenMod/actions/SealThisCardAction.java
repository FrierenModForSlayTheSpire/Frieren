package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import java.util.UUID;

public class SealThisCardAction extends AbstractGameAction {
    UUID uuid;
    public SealThisCardAction(UUID targetUUID) {
        this.setValues(this.target, this.source);
        this.actionType = ActionType.WAIT;
        this.uuid = targetUUID;
    }
    public void update() {
//        AbstractPlayer p = AbstractDungeon.player;
//        this.MoveThisCardToExhaustInCardGroup(p.hand);
//        this.MoveThisCardToExhaustInCardGroup(p.drawPile);
//        this.MoveThisCardToExhaustInCardGroup(p.discardPile);
//        for(AbstractCard c : p.exhaustPile.group){
//            if(c.uuid == this.uuid){
//                p.exhaustPile.removeCard(c);
//            }
//        }
        this.isDone = true;
    }
//    private void MoveThisCardToExhaustInCardGroup(CardGroup cardGroup){
//        AbstractPlayer p = AbstractDungeon.player;
//        for (AbstractCard c : cardGroup.group) {
//            if(c.uuid == this.uuid){
//                this.addToBot(new DestroySpecifiedCardAction(c,cardGroup));
//                if(cardGroup == AbstractDungeon.player.hand){
//                    this.addToBot(new DrawCardAction(1));
//                }
//                this.addToBot(new ApplyPowerAction(p,p,new SealPower(p,1),1));
//            }
//        }
//    }
}
