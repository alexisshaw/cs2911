package Game.testadapter;

import Game.PlayerAction;
import Game.PlayerView;
import Game.testadapter.Activators.*;
import card.Buildings.*;
import card.Character.*;
import framework.interfaces.activators.CardActivator;

/**
 * Created with IntelliJ IDEA.
 * User: Alexis Shaw
 * Date: 5/21/12
 * Time: 3:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class ActivatorFactory {
    public static CardActivator createActivator(
            card.Card c,
            PlayerView myView,
            GameController controller,
            PlayerAction action) {
        if (c.getClass() == AesculapinumCard.class) {
            return AesculapinumActivator.create(myView, controller, action);
        } else if (c.getClass() == ArchitectusCard.class) {
            return ArchitectusActivator.create(myView, controller, action);
        } else if (c.getClass() == CenturioCard.class) {
            return CenturioActivator.create(myView, controller, action);
        } else if (c.getClass() == ConsiliariusCard.class) {
            return ConsiliariusActivator.create(myView, controller, action);
        } else if (c.getClass() == ConsulCard.class) {
            return ConsulActivator.create(myView, controller, action);
        } else if (c.getClass() == EssedumCard.class) {
            return EssedumActivator.create(myView, controller, action);
        } else if (c.getClass() == ForumCard.class) {
            return ForumActivator.create(myView, controller, action);
        } else if (c.getClass() == GladiatorCard.class) {
            return GladiatorActivator.create(myView, controller, action);
        } else if (c.getClass() == HaruspexCard.class) {
            return HaruspexActivator.create(myView, controller, action);
        } else if (c.getClass() == LegatCard.class) {
            return LegatActivator.create(myView, controller, action);
        } else if (c.getClass() == LegionariusCard.class) {
            return LegionariusActivator.create(myView, controller, action);
        } else if (c.getClass() == MachinaCard.class) {
            return MachinaActivator.create(myView, controller, action);
        } else if (c.getClass() == MercatorCard.class) {
            return MercatorActivator.create(myView, controller, action);
        } else if (c.getClass() == MercatusCard.class) {
            return MercatusActivator.create(myView, controller, action);
        } else if (c.getClass() == NeroCard.class) {
            return NeroActivator.create(myView, controller, action);
        } else if (c.getClass() == OnagerCard.class) {
            return OnagerActivator.create(myView, controller, action);
        } else if (c.getClass() == PraetorianusCard.class) {
            return PraetorianusActivator.create(myView, controller, action);
        } else if (c.getClass() == ScaenicusCard.class) {
            return ScaenicusActivator.create(myView, controller, action);
        } else if (c.getClass() == SenatorCard.class) {
            return SenatorActivator.create(myView, controller, action);
        } else if (c.getClass() == SicariusCard.class) {
            return SicariusActivator.create(myView, controller, action);
        } else if (c.getClass() == TribunusPlebusCard.class) {
            return TribunusPlebisActivator.create(myView, controller, action);
        } else if (c.getClass() == VelitesCard.class) {
            return VelitesActivator.create(myView, controller, action);
        } /*else if (c.getClass() == GrimReaperCard.class) {
            return G;
        } else if (c.getClass() == KatCard.class) {
            return Card.KAT;
        } else if (c.getClass() == TelephoneBoxCard.class) {
            return Card.TELEPHONEBOX;
        } */ else {
            return null;
        }
    }
}
