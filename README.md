# Interface Editor

The Interface Editor is a tool for working with the 530+ revision of the RuneScape cache. With some modifications, it can also be used to load OSRS caches. This code was originally written some time ago, and I'm currently refactoring it. While it works, the code can still be improved (working on it!). I'm releasing this tool to help the community.

### Setup

---

1. Open the **config.properties** file.
2. Change the **cache_path** field to your cache path. Make sure to use either `\\` or `/` and that the path ends with one of them.
3. Once you've set up your path, you can either launch the `.jar` file or use your IDE to run `InterfaceGUI.java`.

Example **config.properties**:

```properties
# Tool Properties
cache_path=D:\\server\\data\\cache\\
dump_path=dump/
sprite_path=dump/
version=1
```

### Editing Interfaces

---

- Select an interface on the left.
- Once you click on it, you can select a component from that interface on the right side.

### Setup Information

---

1.  **Step 1**: Open the **config.properties** file and change your path(s).
2.  **Step 2**: Dump your cache sprites. This can be done with the editor by clicking **Extra** -> **Dump Sprites**. If this doesn't dump everything, try using [Steve's editor](https://github.com/szumaster1/sprite-tool).
3.  **Step 3**: **Back up your cache**!
4.  **Step 4**: Enjoy editing! :)

#### Border Information

---

- **Blue border**: Model (not rendered)
- **Red border**: Container
- **Green border**: Base container
- **White border**: Currently selected component

#### Other Information

---

- Make sure you always **back up your cache** before making any changes.
- If you want your component to render using pure `x` and `y` coordinates, use **position mode 0** for both `x` and `y`.

### Troubleshooting

#### My Client Crashed, What Did I Do Wrong?

---

Things to check:

1.  Did I give a component a parent ID that doesn't exist?
2.  Does the sprite exist?
3.  Does the model ID exist?
4.  Is my color ID correct?
5.  Did I modify the scripts?

If everything looks good and the issue persists, try removing the component and testing again. If you're still stuck, feel free to contact me.

#### Questions?

---

> [!NOTE]  
> Please note that the contact information for the author may no longer be valid.

You can reach me via:

- **Discord**: Cara Shnek#6969
- **Rune-Server**: Shnek

I'm happy to help as much as possible, but please note that I won't create entire interfaces for you.

### Script Information

#### How Do Scripts Work in the Script Tab?

---

For example, the transparency script:

```yaml
1357;-2147483645;100;
```

The `code` of the script (not editable in this editor) is as follows:

```java
void script_1357(Widget widget0, int arg1) {
    widget0.setTrans(arg1);
    return;
}
```

#### What Do the Numbers Mean?

---

- `1357`: The **script ID**. The first number in the array is ALWAYS the script ID.
- `-2147483645`: Refers to the widget's own hash. If you want to trigger another component, use its hash.
- `100`: An extra parameter. In this case, it sets the transparency to 100% when hovered over the component.

### Misc Information

---

Here is a list of the current font IDs:

```yaml
305 307 468 473 494 495 496 497 584 591 645 646 647 648 764 776 819 1591 2244 2710 3237 3793 3794 3795 4040 5419 5631 13120 13121
```
