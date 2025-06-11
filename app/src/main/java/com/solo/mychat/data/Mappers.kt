package com.solo.mychat.data

import com.solo.mychat.data.local.entities.GoalEntity
import com.solo.mychat.data.local.entities.MessageEntity
import com.solo.mychat.domain.models.Goal
import com.solo.mychat.domain.models.Message

fun GoalEntity.toGoal() = Goal(
    id = id,
    goalTitle = goalTitle,
    achieved = achieved
)

fun Goal.toGoalEntity() = GoalEntity(
    id = id,
    goalTitle = goalTitle,
    achieved = achieved
)

fun Message.toMessageEntity() = MessageEntity(
    id = id,
    text = text,
    isFromMe = isFromMe
)

fun MessageEntity.toMassage() = Message(
    id = id,
    text = text,
    isFromMe = isFromMe
)