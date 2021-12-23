<div><div data-h5="false" class="content-wrapper-32rgvmtTEZlJxhYe-SXar4"><div class="sub-title-3tQamyyYH5-VXCEHKrzgsd with-action-3ISUSOCo8G5-PfWWWyKDb9">描述</div><div class="react-markdown react-markdown-2P3YjvgELb5tvqGDu8Rkkt"><p>假设你有一个特殊的键盘，键盘上有如下键:</p>
<ul>
<li><code>键1</code>: (A): 在屏幕上打印一个'A'。</li>
<li><code>键2</code>: (Ctrl-A): 选择整个屏幕。</li>
<li><code>键3</code>: (Ctrl-C): 复制选择到缓冲区。</li>
<li><code>键4</code>: (Ctrl-V): 在屏幕上已有的内容后面追加打印缓冲区的内容。</li>
</ul>
<p>现在，你只能按键盘上<strong>N</strong>次(使用以上四个键)，找出你可以在屏幕上打印的“A”的最大数量</p></div>
<li><code>1 &lt;= N &lt;= 50</code></li>
<li>答案将在32位有符号整数的范围内。</li>
</div>
<div >样例</div><div class="react-markdown react-markdown-2P3YjvgELb5tvqGDu8Rkkt"><p><strong>样例 1:</strong></p>
<pre><div><pre style="display: block; overflow-x: auto; background: rgb(43, 43, 43); color: rgb(248, 248, 242); padding: 0.5em;"><code>输入: <span style="color: rgb(245, 171, 53);">3</span>
输出: <span style="color: rgb(245, 171, 53);">3</span>
解释: <span style="color: rgb(220, 198, 224);">A</span>, <span style="color: rgb(220, 198, 224);">A</span>, <span style="color: rgb(220, 198, 224);">A</span></code></pre></div></pre>
<p><strong>样例 2:</strong></p>
<pre><div><pre style="display: block; overflow-x: auto; background: rgb(43, 43, 43); color: rgb(248, 248, 242); padding: 0.5em;"><code>输入: <span style="color: rgb(245, 171, 53);">7</span>
输出: <span style="color: rgb(245, 171, 53);">9</span>
解释: <span style="color: rgb(220, 198, 224);">A</span>, <span style="color: rgb(220, 198, 224);">A</span>, <span style="color: rgb(220, 198, 224);">A</span>, Ctrl <span style="color: rgb(220, 198, 224);">A</span>, Ctrl C, Ctrl V, Ctrl V</code></pre></div></pre></div></div></div>