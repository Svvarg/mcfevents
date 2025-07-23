То как происходит выдача предмета (награды)


```java
// noppes.npcs.NoppesUtilPlayer

    public static void questCompletion(final EntityPlayerMP player, final int questId) {
        final PlayerQuestData playerdata = PlayerDataController.instance.getPlayerData((EntityPlayer)player).questData;
        final QuestData data = playerdata.activeQuests.get(questId);
        if (data == null) {
            return;
        }
        if (!data.quest.questInterface.isCompleted((EntityPlayer)player)) {
            return;
        }
        data.quest.questInterface.handleComplete((EntityPlayer)player);
        if (data.quest.rewardExp > 0) {
            player.field_70170_p.func_72956_a((Entity)player, "random.orb", 0.1f, 0.5f * ((player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.7f + 1.8f));

            player.func_71023_q(data.quest.rewardExp);
        }
        data.quest.factionOptions.addPoints((EntityPlayer)player);

        if (data.quest.mail.isValid()) {
            PlayerDataController.instance.addPlayerMessage(player.func_70005_c_(), data.quest.mail);
        }

        if (!data.quest.randomReward) {
            for (final ItemStack item : data.quest.rewardItems.items.values()) {
                NoppesUtilServer.GivePlayerItem((Entity)player, (EntityPlayer)player, item);
            }
        }
        else { // randomReward == true one item from the list
            final List<ItemStack> list = new ArrayList<ItemStack>();
            for (final ItemStack item2 : data.quest.rewardItems.items.values()) {
                if (item2 != null && item2.func_77973_b() != null) {
                    list.add(item2);
                }
            }
            if (!list.isEmpty()) {
                NoppesUtilServer.GivePlayerItem((Entity)player, (EntityPlayer)player, (ItemStack)list.get(player.func_70681_au().nextInt(list.size())));
            }
        }

        if (!data.quest.command.isEmpty()) {
            NoppesUtilServer.runCommand((EntityPlayer)player, "QuestCompletion", data.quest.command);
        }
        PlayerQuestController.setQuestFinished(data.quest, (EntityPlayer)player);
        if (data.quest.hasNewQuest()) {
            PlayerQuestController.addActiveQuest(data.quest.getNextQuest(), (EntityPlayer)player);
        }
    }
```

```java
// noppes.npcs.NoppesUtilServer

    public static void GivePlayerItem(final Entity entity, final EntityPlayer player, ItemStack item) {
        // field_70170_p - worldObj
        //field_72995_K - isRemote (true for client worlds)
        if (entity.field_70170_p.field_72995_K || item == null) {
            return;
        }
        item = item.func_77946_l(); //copy Returns a new stack with the same properties.
        final float f = 0.7f;
        final double d = entity.field_70170_p.field_73012_v.nextFloat() * f + (double)(1.0f - f);
        final double d2 = entity.field_70170_p.field_73012_v.nextFloat() * f + (double)(1.0f - f);
        final double d3 = entity.field_70170_p.field_73012_v.nextFloat() * f + (double)(1.0f - f);
        final EntityItem entityitem = new EntityItem(entity.field_70170_p, entity.field_70165_t + d, entity.field_70163_u + d2, entity.field_70161_v + d3, item);

        entityitem.field_145804_b = 2;
        entity.field_70170_p.func_72838_d((Entity)entityitem);

        final int i = item.field_77994_a;

        if (player.field_71071_by.func_70441_a(item)) {
            entity.field_70170_p.func_72956_a((Entity)entityitem, "random.pop", 0.2f, ((entity.field_70170_p.field_73012_v.nextFloat() - entity.field_70170_p.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 2.0f);

            player.func_71001_a((Entity)entityitem, i);

            if (item.field_77994_a <= 0) {
                entityitem.func_70106_y();
            }
        }
    }
```
