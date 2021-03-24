# UndoRedoList

An undo-redo data structure based on the concepts of `DoublyLinkedList` and behaves exactly the same way as undo-redo features do in Photoshop and Microsoft Word. 

[Regret](https://github.com/Muddz/Regret) an Android library is uses this data structure.

## How it works

The list adds nodes in sequential order as a `LinkedList` would do when the pointer is standing at end of the list.

Adding on an existing <i>Head</i> node or between existing nodes as shown in the example below will result in all nodes to the right of and inclusive *node 2* being deleted and the new *node 6* taking the end-position of the list

<img src="https://github.com/Muddz/UndoRedoList/blob/master/src/main/resources/AddBetweenElements.png" width="70%">

Each node contains of an instance of [`Action`](https://github.com/Muddz/UndoRedoList/blob/master/src/main/java/Action.java) with field members: `String key` and `Object value` used for holding the key-value of an entry. 

An example of this could be `undoRedo.add(KEY_TEXT_COLOR, Color.RED); `
where `KEY_TEXT_COLOR` is used to identify the type of data and `Color.BLACK` for the actual value.

## Performance
`UndoRedoList` is a linear data structure and has similar performance as Java's LinkedList.
The following is the *time-complexity* for the important methods in `UndoRedoList`

- `add()` is always *O(1)* regardless of the pointer position
- `undo()` or `redo()` is *O(1)* because we can only traverse to the next or previous node


## License

    Copyright 2019 Muddi Walid

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
